package tech.oldhorse.shop.web.config.satoken;

import cn.dev33.satoken.stp.StpInterface;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限加载接口实现类
 */
@Slf4j
@Component
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        log.info("getPermissionList loginId:{}, loginType:{}", loginId, loginType);
        return Lists.newArrayList("*");
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
