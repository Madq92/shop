package tech.oldhorse.shop.service.object.request;

import lombok.Data;

import java.util.List;

@Data
public class UserDelRoleReq {
    private List<String> roleIds;
}
