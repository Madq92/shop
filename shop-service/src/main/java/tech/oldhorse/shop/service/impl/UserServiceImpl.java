package tech.oldhorse.shop.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.oldhorse.shop.common.object.PageData;
import tech.oldhorse.shop.service.UserService;
import tech.oldhorse.shop.service.condition.UserCondition;
import tech.oldhorse.shop.service.convert.UserConvert;
import tech.oldhorse.shop.service.enums.UserStatusEnum;
import tech.oldhorse.shop.service.object.model.UserModel;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserConvert userConvert;

    @Override
    public String create(UserModel userModel) {
        String userId = "123";
        userModel.setUserId(userId);
        userModel.setStatus(UserStatusEnum.ENABLE.name());

        userRepository.save(userConvert.model2Do(userModel));
        return userId;
    }

    @Override
    public Boolean edit(UserModel userModel) {
        UserModel byUserId = getByUserId(userModel.getUserId());


        return userRepository.updateById(userConvert.model2Do(userModel));
    }

    @Override
    public Boolean delete(String userId) {
        return userRepository.lambdaUpdate().eq(UserDO::getUserId, userId).set(UserDO::getDeletedFlag, true).update();
    }

    @Override
    public UserModel getByUserId(String userId) {
        LambdaQueryChainWrapper<UserDO> query = userRepository.lambdaQuery().eq(UserDO::getUserId, userId).eq(UserDO::getDeletedFlag, false);
        UserDO one = userRepository.getOne(query);
        return userConvert.do2Model(one);
    }

    @Override
    public PageData<UserModel> pageByCondition(UserCondition condition) {
        Page<UserDO> page = buildLambdaQuery(condition).page(condition.getPage());
        return PageData.convertFrom(page, userConvert::do2Model);
    }

    @Override
    public List<UserModel> listByCondition(UserCondition condition) {
        List<UserDO> list = buildLambdaQuery(condition).list();
        return userConvert.doList2ModelList(list);
    }

    private LambdaQueryChainWrapper<UserDO> buildLambdaQuery(UserCondition condition) {
        return userRepository.lambdaQuery()
                .eq(UserDO::getDeletedFlag, false)
                .eq(StringUtils.isNotBlank(condition.getTenantId()), UserDO::getTenantId, condition.getTenantId())
                .like(StringUtils.isNotBlank(condition.getNameLike()), UserDO::getName, condition.getNameLike());
    }
}
