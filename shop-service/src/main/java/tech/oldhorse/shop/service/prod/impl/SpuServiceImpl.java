package tech.oldhorse.shop.service.prod.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.oldhorse.shop.common.object.PageData;
import tech.oldhorse.shop.common.utils.AssertUtils;
import tech.oldhorse.shop.dao.prod.entity.SkuDO;
import tech.oldhorse.shop.dao.prod.entity.SpuDO;
import tech.oldhorse.shop.dao.prod.entity.SpuPropDO;
import tech.oldhorse.shop.dao.prod.repository.SkuRepository;
import tech.oldhorse.shop.dao.prod.repository.SpuPropRepository;
import tech.oldhorse.shop.dao.prod.repository.SpuRepository;
import tech.oldhorse.shop.service.prod.CategoryService;
import tech.oldhorse.shop.service.prod.DictService;
import tech.oldhorse.shop.service.prod.SpuService;
import tech.oldhorse.shop.service.prod.condition.DictCondition;
import tech.oldhorse.shop.service.prod.condition.SpuCondition;
import tech.oldhorse.shop.service.prod.convert.SkuCoreConvert;
import tech.oldhorse.shop.service.prod.convert.SpuCoreConvert;
import tech.oldhorse.shop.service.prod.enums.DictTypeEnum;
import tech.oldhorse.shop.service.prod.object.model.CategoryModel;
import tech.oldhorse.shop.service.prod.object.model.DictGroupModel;
import tech.oldhorse.shop.service.prod.object.model.SkuModel;
import tech.oldhorse.shop.service.prod.object.model.SpuModel;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SpuServiceImpl implements SpuService {
    @Autowired
    SpuRepository spuRepository;
    @Autowired
    SkuRepository skuRepository;
    @Autowired
    SpuCoreConvert spuCoreConvert;
    @Autowired
    SkuCoreConvert skuCoreConvert;
    @Autowired
    SpuPropRepository spuPropRepository;

    @Autowired
    DictService dictService;
    @Autowired
    CategoryService categoryService;

    @Override
    public String create(SpuModel spuModel) {
        // 校验分类
        // 校验参数


        // 创建spu
        // 创建sku
        // 创建参数关联关系
        return "";
    }

    @Override
    public Boolean edit(SpuModel spuModel) {
        // 校验分类
        // 校验参数


        // 更新spu
        // 更新sku
        // 删除参数关联关系
        // 创建参数关联关系
        return null;
    }

    @Override
    public Boolean delete(String spuId) {
        // 删除参数关联关系
        // 删除sku
        // 删除spu
        return null;
    }

    @Override
    public SpuModel getBySpuId(String spuId) {
        SpuDO spuDO = spuRepository.getById(spuId);
        SpuModel spuModel = spuCoreConvert.do2Model(spuDO);
        AssertUtils.notNull(spuModel);
        return spuModel;
    }

    @Override
    public SpuModel detaile(String spuId) {
        SpuModel spuModel = getBySpuId(spuId);

        // category
        CategoryModel categoryModel = categoryService.getByCategoryId(spuModel.getCategoryId());
        spuModel.setCategoryName(categoryModel.getName());

        // sku
        List<SkuDO> skuDOS = skuRepository.lambdaQuery().eq(SkuDO::getSpuId, spuId).eq(SkuDO::getDeletedFlag, false).list();
        List<SkuModel> skuModels = skuCoreConvert.doList2ModelList(skuDOS);
        spuModel.setSkus(skuModels);

        // props
        List<SpuPropDO> spuPropDOS = spuPropRepository.lambdaQuery().eq(SpuPropDO::getSpuId, spuId).list();
        if (CollectionUtils.isEmpty(spuPropDOS)) {
            return spuModel;
        }
        DictCondition dictCondition = new DictCondition();
        dictCondition.setDictIdList(spuPropDOS.stream().map(SpuPropDO::getDictId).toList());
        List<DictGroupModel> dictModels = dictService.listByCondition(dictCondition);
        Map<DictTypeEnum, List<DictGroupModel>> dictTypeListMap = dictModels.stream().collect(Collectors.groupingBy(DictGroupModel::getType));

        // TODO
        // -- unit
        // -- label
        // -- sku-spec

        return spuModel;
    }

    private void setSpecNameField(Consumer<String> setSpecName, Supplier<String> getSpecId, Map<String, String> specIdNameMap) {
        String specId = getSpecId.get();
        if (StringUtils.isNotEmpty(specId) && specIdNameMap.containsKey(specId)) {
            setSpecName.accept(specIdNameMap.get(specId));
        }
    }

    @Override
    public PageData<SpuModel> pageByCondition(SpuCondition condition) {
        return PageData.emptyPageData();
    }

    @Override
    public List<SpuModel> listByCondition(SpuCondition condition) {
        return List.of();
    }
}
