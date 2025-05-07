package tech.oldhorse.shop.web.satoken;

import cn.dev33.satoken.stp.StpInterface;
import com.google.common.collect.Lists;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限加载接口实现类
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<String> permCodeList = Lists.newArrayList("*");
        return permCodeList;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return new ArrayList<>();
    }
}
