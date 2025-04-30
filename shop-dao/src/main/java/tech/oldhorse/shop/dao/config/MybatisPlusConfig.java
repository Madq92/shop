package tech.oldhorse.shop.dao.config;


import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.oldhorse.shop.common.context.WebContextHolder;

@Configuration
@MapperScan("tech.oldhorse.shop.dao.mapper")
public class MybatisPlusConfig {
    private static final String TENANT_TABLE = "tenant";
    private static final String TENANT_ID_COLUMN = "tenant_id";

    private static final TenantLineHandler TENANT_LINE_HANDLER = new TenantLineHandler() {
        @Override
        public Expression getTenantId() {
            String tenantId = WebContextHolder.getTenantId();
            if (null == tenantId) {
                tenantId = "0";
            }
            return new StringValue(tenantId);
        }

        @Override
        public String getTenantIdColumn() {
            return TENANT_ID_COLUMN;
        }

        @Override
        public boolean ignoreTable(String tableName) {
            return TENANT_TABLE.equalsIgnoreCase(tableName);
        }
    };

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(TENANT_LINE_HANDLER));
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }
}