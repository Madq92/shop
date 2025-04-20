package tech.oldhorse.shop.service.condition;

import lombok.Data;

@Data
public class UserCondition {
    private String tenantId;
    private String nameLike;
}
