package tech.oldhorse.shop.web.controller.sys;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.oldhorse.shop.common.object.Result;
import tech.oldhorse.shop.service.sys.ConfigService;
import tech.oldhorse.shop.service.sys.condition.ConfigCondition;
import tech.oldhorse.shop.service.sys.convert.ConfigCoreConvert;
import tech.oldhorse.shop.service.sys.object.dto.ConfigDTO;
import tech.oldhorse.shop.service.sys.object.model.ConfigModel;

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
    private ConfigCoreConvert configCoreConvert;

    @SaCheckPermission("config.view")
    @Operation(summary = "参数配置列表")
    @GetMapping
    public Result<List<ConfigDTO>> list(@RequestParam(required = false, value = "configType") String configType,
                                        @RequestParam(required = false, value = "configKey") String configKey,
                                        @RequestParam(required = false, value = "configName") String configName) {
        ConfigCondition condition = new ConfigCondition();
        condition.setConfigKey(configKey);
        condition.setConfigType(configType);
        condition.setConfigNameLike(configName);
        List<ConfigModel> configModels = configService.listByCondition(condition);
        return Result.success(configCoreConvert.modelList2DtoList(configModels));
    }

    @SaCheckPermission("config.view")
    @Operation(summary = "参数配置详情")
    @GetMapping("/{configId}")
    public Result<ConfigDTO> detail(@PathVariable("configId") String configId) {
        ConfigModel configModel = configService.getByConfigId(configId);
        return Result.success(configModel, configCoreConvert::model2Dto);
    }

    @SaCheckPermission("config.create")
    @Operation(summary = "参数配置创建")
    @PostMapping
    public Result<String> create(@RequestBody ConfigDTO configDTO) {
        String configId = configService.create(configCoreConvert.dto2Model(configDTO));
        return Result.success(configId);
    }

    @SaCheckPermission("config.update")
    @Operation(summary = "参数配置编辑")
    @PutMapping("/{configId}")
    public Result<Boolean> edit(@PathVariable("configId") String configId,
                                @RequestBody ConfigDTO configDTO) {
        configDTO.setConfigId(configId);
        return Result.success(configService.edit(configCoreConvert.dto2Model(configDTO)));
    }

    @SaCheckPermission("config.delete")
    @Operation(summary = "参数配置删除")
    @DeleteMapping("/{configId}")
    public Result<Boolean> delete(@PathVariable("configId") String configId) {
        return Result.success(configService.delete(configId));
    }
}
