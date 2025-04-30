package tech.oldhorse.shop.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.oldhorse.shop.dao.repository.ConfigRepository;
import tech.oldhorse.shop.integration.sequence.wrapper.IdGeneratorWrapper;
import tech.oldhorse.shop.service.ConfigService;
import tech.oldhorse.shop.service.condition.ConfigCondition;
import tech.oldhorse.shop.service.convert.ConfigCoreConvert;
import tech.oldhorse.shop.service.object.model.ConfigModel;

import java.util.List;

@Slf4j
@Service
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    ConfigRepository configRepository;
    @Autowired
    ConfigCoreConvert configCoreConvert;
    @Autowired
    IdGeneratorWrapper idGenerator;

    @Override
    public List<ConfigModel> listByCondition(ConfigCondition condition) {
        return List.of();
    }

    @Override
    public ConfigModel getByConfigId(String configId) {
        return null;
    }

    @Override
    public String create(ConfigModel configModel) {
        return "";
    }

    @Override
    public Boolean edit(ConfigModel configModel) {
        return null;
    }

    @Override
    public Boolean delete(String configId) {
        return null;
    }
}
