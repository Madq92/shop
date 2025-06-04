package tech.oldhorse.shop.service.prod.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.oldhorse.shop.common.utils.AssertUtils;
import tech.oldhorse.shop.dao.prod.entity.DictDO;
import tech.oldhorse.shop.dao.prod.entity.DictGroupDO;
import tech.oldhorse.shop.dao.prod.repository.DictGroupRepository;
import tech.oldhorse.shop.dao.prod.repository.DictRepository;
import tech.oldhorse.shop.integration.sequence.wrapper.IdGeneratorWrapper;
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
    @Autowired
    private IdGeneratorWrapper idGenerator;

    @Override
    public String createGroup(DictGroupModel dictGroupModel) {
        // name + type 不能重复
        AssertUtils.notNull(dictGroupModel.getName(), "name不能为空");
        AssertUtils.notNull(dictGroupModel.getType(), "type不能为空");

        DictCondition condition = new DictCondition();
        condition.setGroupName(dictGroupModel.getName());
        condition.setType(dictGroupModel.getType());
        List<DictGroupModel> dictGroupModels = listByCondition(condition);
        AssertUtils.isEmpty(dictGroupModels, "名称和类型不能重复");

        String groupId = idGenerator.nextStringId();
        dictGroupModel.setDictGroupId(groupId);

        DictGroupDO dictGroupDO = dictGroupCoreConvert.model2Do(dictGroupModel);
        dictGroupRepository.save(dictGroupDO);
        return groupId;
    }

    @Override
    public Boolean editGroup(DictGroupModel dictGroupModel) {
        DictGroupModel byDictGroupId = getByDictGroupId(dictGroupModel.getDictGroupId());
        AssertUtils.notNull(byDictGroupId, "分组不存在");

        // name + type 不能重复
        AssertUtils.notNull(dictGroupModel.getName(), "name不能为空");
        AssertUtils.isEquals(dictGroupModel.getType(), byDictGroupId.getType(), "type不能修改");

        DictCondition condition = new DictCondition();
        condition.setGroupName(dictGroupModel.getName());
        condition.setType(dictGroupModel.getType());
        List<DictGroupModel> dictGroupModels = listByCondition(condition);
        List<DictGroupModel> list = dictGroupModels.stream().filter(group -> !group.getDictGroupId().equals(byDictGroupId.getDictGroupId())).toList();
        AssertUtils.isEmpty(list, "名称和类型不能重复");

        DictGroupDO dictGroupDO = dictGroupCoreConvert.model2Do(dictGroupModel);
        dictGroupDO.setId(byDictGroupId.getId());
        return dictGroupRepository.updateById(dictGroupDO);
    }

    @Override
    public Boolean deleteGroup(String dictGroupId) {
        DictGroupModel byDictGroupId = getByDictGroupId(dictGroupId);
        AssertUtils.notNull(byDictGroupId, "分组不存在");

        // TODO 有详情不能删除


        byDictGroupId.setDeletedFlag(true);
        DictGroupDO dictGroupDO = dictGroupCoreConvert.model2Do(byDictGroupId);
        return dictGroupRepository.updateById(dictGroupDO);
    }

    @Override
    public DictGroupModel getByDictGroupId(String dictGroupId) {
        DictGroupModel byDictGroupId = getByDictGroupId(dictGroupId);
        AssertUtils.notNull(byDictGroupId, "分组不存在");
        return byDictGroupId;
    }

    @Override
    public DictGroupModel groupDetaile(String dictGroupId) {
        DictGroupModel byDictGroupId = getByDictGroupId(dictGroupId);

        byDictGroupId.setDictDetails(listDetailByGroupId(dictGroupId));
        return byDictGroupId;
    }

    @Override
    public String createDetail(DictModel dictModel) {
        // name + type 不能重复
        AssertUtils.notNull(dictModel.getName(), "name不能为空");
        AssertUtils.notNull(dictModel.getType(), "type不能为空");

        DictCondition condition = new DictCondition();
        condition.setName(dictModel.getName());
        condition.setType(dictModel.getType());
        List<DictGroupModel> dictGroupModels = listByCondition(condition);
        AssertUtils.isEmpty(dictGroupModels, "名称和类型不能重复");

        String dictId = idGenerator.nextStringId();
        dictModel.setDictId(dictId);

        DictDO dictDO = dictCoreConvert.model2Do(dictModel);
        dictRepository.save(dictDO);
        return dictId;
    }

    @Override
    public Boolean editDetail(DictModel dictModel) {
        DictModel byDictId = getByDictId(dictModel.getDictId());
        AssertUtils.notNull(byDictId, "字典不存在");

        DictDO dictDO = dictCoreConvert.model2Do(dictModel);
        return dictRepository.updateById(dictDO);
    }

    @Override
    public Boolean deleteDetail(String dictId) {
        DictModel byDictId = getByDictId(dictId);
        AssertUtils.notNull(byDictId, "字典不存在");

        DictDO dictDO = dictCoreConvert.model2Do(byDictId);
        dictDO.setDeletedFlag(true);
        return dictRepository.updateById(dictDO);
    }

    @Override
    public DictModel getByDictId(String dictId) {
        DictDO one = dictRepository.lambdaQuery().eq(DictDO::getDictId, dictId).eq(DictDO::getDeletedFlag, false).one();
        return dictCoreConvert.do2Model(one);
    }

    @Override
    public List<DictModel> listDetailByIds(List<String> dictIds) {
        if (CollectionUtils.isEmpty(dictIds)) {
            return List.of();
        }

        List<DictDO> list = dictRepository.lambdaQuery().in(DictDO::getDictId, dictIds).eq(DictDO::getDeletedFlag, false).list();
        return dictCoreConvert.doList2ModelList(list);
    }

    @Override
    public List<DictModel> listDetailByGroupId(String dictGroupId) {
        List<DictDO> list = dictRepository.lambdaQuery().eq(DictDO::getDictGroupId, dictGroupId).eq(DictDO::getDeletedFlag, false).list();
        return dictCoreConvert.doList2ModelList(list);
    }

    @Override
    public List<DictGroupModel> listByCondition(DictCondition condition) {
        return List.of();
    }
}
