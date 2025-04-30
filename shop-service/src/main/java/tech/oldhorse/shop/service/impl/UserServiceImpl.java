package tech.oldhorse.shop.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.oldhorse.shop.common.object.PageData;
import tech.oldhorse.shop.dao.entity.UserDO;
import tech.oldhorse.shop.dao.repository.UserRepository;
import tech.oldhorse.shop.integration.sequence.wrapper.IdGeneratorWrapper;
import tech.oldhorse.shop.service.UserService;
import tech.oldhorse.shop.service.condition.UserCondition;
import tech.oldhorse.shop.service.convert.UserCoreConvert;
import tech.oldhorse.shop.service.enums.UserStatusEnum;
import tech.oldhorse.shop.service.object.model.UserModel;
import tech.oldhorse.shop.service.object.request.RoleAddResourceReq;
import tech.oldhorse.shop.service.object.request.RoleDelResourceReq;
import tech.oldhorse.shop.service.object.request.UserLoginReq;
import tech.oldhorse.shop.service.object.request.UserUpdatePasswordReq;
import tech.oldhorse.shop.service.object.response.UserLoginInfoResp;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserCoreConvert userCoreConvert;
    @Autowired
    IdGeneratorWrapper idGenerator;

    @Override
    public String create(UserModel userModel) {
        String userId = idGenerator.nextStringId();
        userModel.setUserId(userId);
        userModel.setStatus(UserStatusEnum.ENABLE);
        userRepository.save(userCoreConvert.model2Do(userModel));
        return userId;
    }

    @Override
    public Boolean edit(UserModel userModel) {
        UserModel userInDb = getByUserId(userModel.getUserId());

        UserDO userDO = userCoreConvert.model2Do(userModel);
        userDO.setId(userInDb.getId());
        return userRepository.updateById(userCoreConvert.model2Do(userInDb));
    }

    @Override
    public Boolean delete(String userId) {
        return userRepository.lambdaUpdate().eq(UserDO::getUserId, userId).set(UserDO::getDeletedFlag, true).update();
    }

    @Override
    public UserModel getByUserId(String userId) {
        UserDO one = userRepository.lambdaQuery()
                .eq(UserDO::getUserId, userId)
                .eq(UserDO::getDeletedFlag, false)
                .one();

        return userCoreConvert.do2Model(one);
    }

    @Override
    public PageData<UserModel> pageByCondition(UserCondition condition) {
        Page<UserDO> page = buildLambdaQuery(condition).page(condition.getPage());
        return PageData.convertFrom(page, userCoreConvert::do2Model);
    }

    @Override
    public List<UserModel> listByCondition(UserCondition condition) {
        List<UserDO> list = buildLambdaQuery(condition).list();
        return userCoreConvert.doList2ModelList(list);
    }

    @Override
    public Boolean updatePassword(String userId, UserUpdatePasswordReq req) {
        return null;
    }

    @Override
    public UserLoginInfoResp loginInfo(String userId) {
        return null;
    }

    @Override
    public UserLoginInfoResp login(UserLoginReq req) {
        return null;
    }

    @Override
    public Boolean logout(String userId) {
        return null;
    }

    @Override
    public Boolean addRole(String userId, RoleAddResourceReq req) {
        return null;
    }

    @Override
    public Boolean delRole(String userId, RoleDelResourceReq req) {
        return null;
    }

    private LambdaQueryChainWrapper<UserDO> buildLambdaQuery(UserCondition condition) {
        return userRepository.lambdaQuery()
                .eq(UserDO::getDeletedFlag, false)
                .eq(StringUtils.isNotBlank(condition.getTenantId()), UserDO::getTenantId, condition.getTenantId())
                .like(StringUtils.isNotBlank(condition.getNameLike()), UserDO::getName, condition.getNameLike());
    }
}
