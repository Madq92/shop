package ${package.Service};

import ${package.Entity}.${entity};
import com.baomidou.mybatisplus.extension.service.IService;

public interface ${table.serviceName} extends IService<${entity}> {

// 自定义 Service 方法示例aaa
String customServiceMethod();

}