package tech.oldhorse.shop.dao.plugins;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.defaults.DefaultSqlSession.StrictMap;
import tech.oldhorse.shop.common.object.BaseEntity;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * BaseEntity数据设置
 */
@Intercepts({@Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class, Integer.class})})
public class BaseDataInterceptor implements Interceptor {

    public static <T> T realTarget(Object target) {
        if (Proxy.isProxyClass(target.getClass())) {
            MetaObject metaObject = SystemMetaObject.forObject(target);
            return realTarget(metaObject.getValue("h.target"));
        }
        return (T) target;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        RoutingStatementHandler handler = realTarget(invocation.getTarget());
        BoundSql boundSql = handler.getBoundSql();

        //通过MetaObject优雅访问对象的属性，这里是访问statementHandler的属性
        MetaObject metaObject = MetaObject.forObject(handler, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        // 配置文件中SQL语句的ID
        String id = mappedStatement.getId();
        boolean insertOps = id.toLowerCase().matches("tech\\.oldhorse\\.shop\\.dao\\..*insert.*");
        boolean updateOps = id.toLowerCase().matches("tech\\.oldhorse\\.shop\\.dao\\..*update.*");
        if (!insertOps && !updateOps) {
            return invocation.proceed();
        }
        Object obj = boundSql.getParameterObject();

        if (obj instanceof BaseEntity baseEntity) {
            buildOperatorInfo(baseEntity, insertOps);
        } else if (obj instanceof StrictMap) {
            HashMap hashMap = (HashMap) obj;
            List<Object> list = (List<Object>) hashMap.get("list");

            if (!CollectionUtils.isEmpty(list)) {
                list.stream().filter(Objects::nonNull).forEach(e -> {
                    if (e instanceof BaseEntity bEntity) {
                        buildOperatorInfo(bEntity, insertOps);
                    }
                });
            }
        }
        return invocation.proceed();
    }

    private void buildOperatorInfo(BaseEntity baseEntity, boolean insertOps) {
        baseEntity.setTenantId("100");
    }
}
