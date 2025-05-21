package tech.oldhorse.shop.web.config.satoken;

import cn.dev33.satoken.stp.StpInterface;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.oldhorse.shop.common.constants.CommonConstants;
import tech.oldhorse.shop.service.UserService;
import tech.oldhorse.shop.service.object.model.ResourceModel;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 自定义权限加载接口实现类
 */
@Slf4j
@Component
public class StpInterfaceImpl implements StpInterface {
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private UserService userService;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        log.info("getPermissionList loginId:{}, loginType:{}", loginId, loginType);
        String cacheKey = CommonConstants.getUserPermCacheKey(loginId, loginType);
        RSet<Object> premSet = redissonClient.getSet(cacheKey);

        if (premSet.isEmpty()) {
            List<ResourceModel> userResource = userService.getUserResource(loginId.toString());
            List<String> dbPermList = userResource.stream().map(ResourceModel::getPerms).filter(Objects::nonNull).toList();
            if (dbPermList.isEmpty()) {
                // 防止没有资源权限的用户每次都查库
                dbPermList = List.of("1");
            }
            // 将权限信息写入 Redis 缓存
            premSet.addAll(dbPermList);
            premSet.expire(Duration.ofHours(1));
            return dbPermList;
        }
        return premSet.stream().map(String::valueOf).toList();
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        log.info("getRoleList loginId:{}, loginType:{}", loginId, loginType);
        return new ArrayList<>();
    }
}
