package tech.oldhorse.shop.service.prod.condition;

import lombok.Data;

import java.util.List;

@Data
public class DictCondition {
    private String nameLike;
    private List<String> dictIdList;
    private List<String> dictGroupIdList;
}
