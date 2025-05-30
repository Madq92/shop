package tech.oldhorse.shop.service.prod.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum YesOrNoEnum {
    Y("YES"),
    N("NO");
    private String desc;
}
