package tech.oldhorse.shop.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.oldhorse.shop.common.object.Result;
import tech.oldhorse.shop.service.ConfigService;
import tech.oldhorse.shop.service.condition.ConfigCondition;
import tech.oldhorse.shop.service.object.dto.ConfigDTO;
import tech.oldhorse.shop.service.object.model.ConfigModel;
import tech.oldhorse.shop.web.convert.ConfigConvert;

import java.util.List;

/**
 * <p>
 * 参数配置表 前端控制器
 * </p>
 *
 * @author mika
 * @since 2025-04-23
 */
@Tag(name = "参数配置管理", description = "参数配置的增删改查操作")
@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @Autowired
    private ConfigConvert configConvert;

    @Operation(summary = "参数配置列表")
    @GetMapping
    public Result<List<ConfigDTO>> list() {
        ConfigCondition condition = new ConfigCondition();
        List<ConfigModel> configModels = configService.listByCondition(condition);
        return Result.success(configConvert.modelList2DtoList(configModels));
    }

    @Operation(summary = "参数配置详情")
    @GetMapping("/{configId}")
    public Result<ConfigDTO> detail(@PathVariable("configId") String configId) {
        ConfigModel configModel = configService.getByConfigId(configId);
        return Result.success(configModel, configConvert::model2Dto);
    }

    @Operation(summary = "参数配置创建")
    @PostMapping
    public Result<String> create(@RequestBody ConfigDTO configDTO) {
        String configId = configService.create(configConvert.dto2Model(configDTO));
        return Result.success(configId);
    }

    @Operation(summary = "参数配置编辑")
    @PutMapping("/{configId}")
    public Result<Boolean> edit(@PathVariable("configId") String configId,
                                @RequestBody ConfigDTO configDTO) {
        configDTO.setConfigId(configId);
        return Result.success(configService.edit(configConvert.dto2Model(configDTO)));
    }

    @Operation(summary = "参数配置删除")
    @DeleteMapping("/{configId}")
    public Result<Boolean> delete(@PathVariable("configId") String configId) {
        return Result.success(configService.delete(configId));
    }
}
