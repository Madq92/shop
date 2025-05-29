package tech.oldhorse.shop.service.sys;

import tech.oldhorse.shop.service.sys.condition.ConfigCondition;
import tech.oldhorse.shop.service.sys.object.model.ConfigModel;

import java.util.List;

public interface ConfigService {
    List<ConfigModel> listByCondition(ConfigCondition condition);

    ConfigModel getByConfigId(String configId);

    String create(ConfigModel configModel);

    Boolean edit(ConfigModel configModel);

    Boolean delete(String configId);
}
