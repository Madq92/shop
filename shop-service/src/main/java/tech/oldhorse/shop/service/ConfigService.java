package tech.oldhorse.shop.service;

import tech.oldhorse.shop.service.condition.ConfigCondition;
import tech.oldhorse.shop.service.object.model.ConfigModel;

import java.util.List;

public interface ConfigService {
    List<ConfigModel> listByCondition(ConfigCondition condition);

    ConfigModel getByConfigId(String configId);

    String create(ConfigModel configModel);

    Boolean edit(ConfigModel configModel);

    Boolean delete(String configId);
}
