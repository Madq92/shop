package ${package.Controller};

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import ${package.Service}.${table.serviceName};
<#if superControllerClassPackage??>
    import ${superControllerClassPackage};
</#if>

@RestController
@RequestMapping("/${table.entityPath}")
<#if superControllerClass??>
    public class ${table.controllerName} extends ${superControllerClass} {
<#else>
    public class ${table.controllerName} {
</#if>

@Autowired
private ${table.serviceName} ${table.entityPath}Service;

// 自定义方法示例aaa
@GetMapping("/customMethod")
public String customMethod() {
return "This is a custom controller method!";
}

}