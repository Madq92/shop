package tech.oldhorse.shop.service.prod.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.oldhorse.shop.dao.prod.repository.DictGroupRepository;
import tech.oldhorse.shop.dao.prod.repository.DictRepository;
import tech.oldhorse.shop.service.prod.DictService;
import tech.oldhorse.shop.service.prod.condition.DictCondition;
import tech.oldhorse.shop.service.prod.convert.DictCoreConvert;
import tech.oldhorse.shop.service.prod.convert.DictGroupCoreConvert;
import tech.oldhorse.shop.service.prod.object.model.DictGroupModel;
import tech.oldhorse.shop.service.prod.object.model.DictModel;

import java.util.List;

@Slf4j
@Service
public class DictServiceImpl implements DictService {
    @Autowired
    private DictRepository dictRepository;
    @Autowired
    private DictGroupRepository dictGroupRepository;
    @Autowired
    private DictCoreConvert dictCoreConvert;
    @Autowired
    private DictGroupCoreConvert dictGroupCoreConvert;

    @Override
    public String createGroup(DictGroupModel dictGroupModel) {
        return "";
    }

    @Override
    public Boolean editGroup(DictGroupModel dictGroupModel) {
        return null;
    }

    @Override
    public Boolean deleteGroup(String dictGroupId) {
        return null;
    }

    @Override
    public DictGroupModel getByDictGroupId(String dictGroupId) {
        return null;
    }

    @Override
    public DictGroupModel groupDetaile(String dictGroupId) {
        return null;
    }

    @Override
    public String createDetail(DictModel dictModel) {
        return "";
    }

    @Override
    public Boolean editDetail(DictModel dictModel) {
        return null;
    }

    @Override
    public Boolean deleteDetail(String dictId) {
        return null;
    }

    @Override
    public DictModel getByDictId(String dictId) {
        return null;
    }

    @Override
    public List<DictModel> listDetailByIds(List<String> dictIds) {
        return List.of();
    }

    @Override
    public List<DictGroupModel> listByCondition(DictCondition condition) {
        return List.of();
    }
}
