package tech.oldhorse.shop.service.impl;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.oldhorse.shop.common.constants.CommonConstants;
import tech.oldhorse.shop.common.exception.BaseParamException;
import tech.oldhorse.shop.common.exception.UnauthorizedException;
import tech.oldhorse.shop.common.object.PageData;
import tech.oldhorse.shop.common.utils.AssertUtils;
import tech.oldhorse.shop.common.utils.RsaUtil;
import tech.oldhorse.shop.dao.entity.UserDO;
import tech.oldhorse.shop.dao.entity.UserRoleDO;
import tech.oldhorse.shop.dao.repository.UserRepository;
import tech.oldhorse.shop.dao.repository.UserRoleRepository;
import tech.oldhorse.shop.integration.sequence.wrapper.IdGeneratorWrapper;
import tech.oldhorse.shop.service.ResourceService;
import tech.oldhorse.shop.service.RoleService;
import tech.oldhorse.shop.service.UserService;
import tech.oldhorse.shop.service.condition.ResourceCondition;
import tech.oldhorse.shop.service.condition.RoleCondition;
import tech.oldhorse.shop.service.condition.UserCondition;
import tech.oldhorse.shop.service.convert.ResourceCoreConvert;
import tech.oldhorse.shop.service.convert.RoleCoreConvert;
import tech.oldhorse.shop.service.convert.UserCoreConvert;
import tech.oldhorse.shop.service.enums.UserStatusEnum;
import tech.oldhorse.shop.service.object.model.ResourceModel;
import tech.oldhorse.shop.service.object.model.RoleModel;
import tech.oldhorse.shop.service.object.model.UserModel;
import tech.oldhorse.shop.service.object.request.UserAddRoleReq;
import tech.oldhorse.shop.service.object.request.UserDelRoleReq;
import tech.oldhorse.shop.service.object.request.UserLoginReq;
import tech.oldhorse.shop.service.object.request.UserUpdatePasswordReq;
import tech.oldhorse.shop.service.object.response.UserLoginInfoResp;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserCoreConvert userCoreConvert;
    @Autowired
    IdGeneratorWrapper idGenerator;
    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    RoleService roleService;
    @Autowired
    ResourceService resourceService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleCoreConvert roleCoreConvert;
    @Autowired
    ResourceCoreConvert resourceCoreConvert;

    @Override
    public String create(UserModel userModel) {
        String userId = idGenerator.nextStringId();
        userModel.setUserId(userId);
        userModel.setStatus(UserStatusEnum.ENABLE);

        userModel.setPassword(passwordEncoder.encode("123456"));

        userRepository.save(userCoreConvert.model2Do(userModel));
        return userId;
    }

    @Override
    public Boolean edit(UserModel userModel) {
        UserModel userInDb = getByUserId(userModel.getUserId());
        AssertUtils.notNull(userInDb);

        UserDO userDO = userCoreConvert.model2Do(userModel);
        userDO.setId(userInDb.getId());
        return userRepository.updateById(userDO);
    }

    @Override
    public Boolean delete(String userId) {
        UserModel userInDb = getByUserId(userId);
        AssertUtils.notNull(userInDb);

        UserDO userDO = new UserDO();
        userDO.setId(userInDb.getId());
        userDO.setDeletedFlag(true);
        return userRepository.updateById(userDO);
    }

    @Override
    public UserModel getByUserId(String userId) {
        UserDO one = userRepository.lambdaQuery()
                .eq(UserDO::getUserId, userId)
                .eq(UserDO::getDeletedFlag, false)
                .one();
        AssertUtils.notNull(one);

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
    public List<ResourceModel> getUserResource(String userId) {
        // 获取用户角色
        List<UserRoleDO> userRoleDOS = userRoleRepository.lambdaQuery().eq(UserRoleDO::getUserId, userId).list();
        if (CollectionUtils.isEmpty(userRoleDOS)) {
            return List.of();
        }

        // 获取角色对应得资源
        List<String> roleIds = userRoleDOS.stream().map(UserRoleDO::getRoleId).toList();

        ResourceCondition resourceCondition = new ResourceCondition();
        resourceCondition.setResourceIds(roleIds);
        return resourceService.listByCondition(resourceCondition);
    }

    @Override
    public List<RoleModel> getUserRole(String userId) {
        // 获取用户角色
        List<UserRoleDO> userRoleDOS = userRoleRepository.lambdaQuery().eq(UserRoleDO::getUserId, userId).list();
        if (CollectionUtils.isEmpty(userRoleDOS)) {
            return List.of();
        }

        RoleCondition roleCondition = new RoleCondition();
        roleCondition.setRoleIds(userRoleDOS.stream().map(UserRoleDO::getRoleId).toList());
        return roleService.listByCondition(roleCondition);
    }

    @Override
    public Boolean addRole(String userId, UserAddRoleReq req) {
        UserModel user = getByUserId(userId);
        AssertUtils.notNull(user, "用户不存在");

        RoleCondition roleCondition = new RoleCondition();
        roleCondition.setRoleIds(req.getRoleIds());
        List<RoleModel> roleModels = roleService.listByCondition(roleCondition);
        AssertUtils.notNull(roleModels, "角色不存在");

        List<UserRoleDO> list = roleModels.stream().map(roleModel -> new UserRoleDO(userId, roleModel.getRoleId())).toList();
        return userRoleRepository.saveBatch(list);
    }

    @Override
    public Boolean delRole(String userId, UserDelRoleReq req) {
        List<UserRoleDO> list = userRoleRepository.lambdaQuery().eq(UserRoleDO::getUserId, userId).in(UserRoleDO::getRoleId, req.getRoleIds()).list();
        if (CollectionUtils.isEmpty(list)) {
            return true;
        }
        return userRoleRepository.removeBatchByIds(list.stream().map(UserRoleDO::getId).collect(Collectors.toList()));
    }

    private LambdaQueryChainWrapper<UserDO> buildLambdaQuery(UserCondition condition) {
        return userRepository.lambdaQuery()
                .eq(UserDO::getDeletedFlag, false)
                .eq(StringUtils.isNotBlank(condition.getEmail()), UserDO::getEmail, condition.getEmail())
                .eq(StringUtils.isNotBlank(condition.getPhonenumber()), UserDO::getPhonenumber, condition.getPhonenumber())
                .like(StringUtils.isNotBlank(condition.getNameLike()), UserDO::getName, condition.getNameLike());
    }


    @Override
    public UserLoginInfoResp loginInfo(String userId) {
        UserModel userModel = this.getByUserId(userId);

        UserLoginInfoResp resp = new UserLoginInfoResp();
        resp.setUser(userCoreConvert.model2Dto(userModel));
        resp.setRoleList(roleCoreConvert.modelList2DtoList(getUserRole(userId)));
        resp.setResourceList(resourceCoreConvert.modelList2DtoList(getUserResource(userId)));

        return resp;
    }

    @Override
    public UserLoginInfoResp login(UserLoginReq req) {
        // 1.获取用户
        UserCondition condition = new UserCondition();
        if (StringUtils.isNotBlank(req.getEmail())) {
            condition.setEmail(req.getEmail());
        } else if (StringUtils.isNotBlank(req.getPhonenumber())) {
            condition.setPhonenumber(req.getPhonenumber());
        } else {
            throw new BaseParamException("请输入邮箱或者电话号码登录");
        }
        UserDO userDO = buildLambdaQuery(condition).one();
        UserModel userModel = userCoreConvert.do2Model(userDO);
        if (null == userModel) {
            throw new UnauthorizedException("用户未找到");
        }

        // 2.验证密码
        String password = URLDecoder.decode(req.getPassword(), StandardCharsets.UTF_8);
        if (CommonConstants.FRONT_PASSWORD_ENCODE) {
            password = RsaUtil.decrypt(password, CommonConstants.PRIVATE_KEY);
        }
        if (!passwordEncoder.matches(password, userModel.getPassword())) {
            throw new UnauthorizedException("账号密码错误");
        }

        // 3.验证用户状态
        if (UserStatusEnum.DISABLE.equals(userModel.getStatus())) {
            throw new UnauthorizedException("用户已停用");
        }

        // 4.验证成功，生成token，缓存资源权限
        String userId = userModel.getUserId();
        StpUtil.login(userId, CommonConstants.TOKEN_TIMEOUT);
        SaSession session = StpUtil.getTokenSession();
        UserLoginInfoResp resp = new UserLoginInfoResp();
        resp.setToken(session.getToken());
        resp.setUser(userCoreConvert.model2Dto(userModel));
        resp.setRoleList(roleCoreConvert.modelList2DtoList(getUserRole(userId)));
        resp.setResourceList(resourceCoreConvert.modelList2DtoList(getUserResource(userId)));

        // TODO 缓存用户资源权限
        return resp;
    }

    @Override
    public Boolean logout(String userId) {
        StpUtil.logout(userId);
        return true;
    }

    @Override
    public Boolean updatePassword(String userId, UserUpdatePasswordReq req) {
        UserModel userModel = this.getByUserId(userId);

        // 验证旧密码
        String oldPassword = URLDecoder.decode(req.getOldPassword(), StandardCharsets.UTF_8);
        String decryptedOldPassword = RsaUtil.decrypt(oldPassword, CommonConstants.PRIVATE_KEY);
        if (!passwordEncoder.matches(decryptedOldPassword, userModel.getPassword())) {
            throw new UnauthorizedException("账号密码错误");
        }

        // 更新新密码
        String decryptedNewPassword = RsaUtil.decrypt(req.getNewPassword(), CommonConstants.PRIVATE_KEY);

        UserDO userDO = new UserDO();
        userDO.setId(userModel.getId());
        userDO.setPassword(passwordEncoder.encode(decryptedNewPassword));
        return userRepository.updateById(userDO);
    }


}
