package tech.oldhorse.shop.service.object.model;

import lombok.Data;
import tech.oldhorse.shop.common.object.BaseModel;

import java.util.List;

@Data
public class RoleModel extends BaseModel {
    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色权限字符串
     */
    private String roleKey;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 资源列表
     */
    private List<ResourceModel> resources;
}
