package tech.oldhorse.shop.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.oldhorse.shop.common.utils.AssertUtils;
import tech.oldhorse.shop.dao.entity.ConfigDO;
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
    ConfigCoreConvert configConvert;
    @Autowired
    IdGeneratorWrapper idGenerator;

    @Override
    public List<ConfigModel> listByCondition(ConfigCondition condition) {
        List<ConfigDO> list = configRepository.lambdaQuery().eq(ConfigDO::getDeletedFlag, false).list();
        return configConvert.doList2ModelList(list);
    }

    @Override
    public ConfigModel getByConfigId(String configId) {
        ConfigDO one = configRepository.lambdaQuery().eq(ConfigDO::getDeletedFlag, false).eq(ConfigDO::getConfigId, configId).one();
        AssertUtils.notNull(one);
        return configConvert.do2Model(one);
    }

    @Override
    public String create(ConfigModel configModel) {
        String configId = idGenerator.nextStringId();
        configModel.setConfigId(configId);

        configRepository.save(configConvert.model2Do(configModel));
        return configId;
    }

    @Override
    public Boolean edit(ConfigModel configModel) {
        ConfigModel configInDb = getByConfigId(configModel.getConfigId());
        AssertUtils.notNull(configInDb);

        ConfigDO configDO = configConvert.model2Do(configModel);
        configDO.setId(configInDb.getId());
        return configRepository.updateById(configDO);
    }

    @Override
    public Boolean delete(String configId) {
        ConfigModel configInDb = getByConfigId(configId);
        AssertUtils.notNull(configInDb);

        ConfigDO configDO = new ConfigDO();
        configDO.setId(configInDb.getId());
        configDO.setDeletedFlag(true);
        return configRepository.updateById(configDO);
    }
}
