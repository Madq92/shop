package tech.oldhorse.shop.service.object.request;

import lombok.Data;

import java.util.List;

@Data
public class RoleDelResourceReq {
    private List<String> resourceIds;
}
