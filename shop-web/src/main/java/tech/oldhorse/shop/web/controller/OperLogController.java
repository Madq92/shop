package tech.oldhorse.shop.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.oldhorse.shop.common.object.PageData;
import tech.oldhorse.shop.common.object.Result;
import tech.oldhorse.shop.common.utils.PageUtil;
import tech.oldhorse.shop.service.OperLogService;
import tech.oldhorse.shop.service.condition.OperLogCondition;
import tech.oldhorse.shop.service.object.dto.OperLogDTO;
import tech.oldhorse.shop.service.object.model.OperLogModel;
import tech.oldhorse.shop.web.convert.OperLogConvert;

/**
 * <p>
 * 操作日志记录 前端控制器
 * </p>
 *
 * @author mika
 * @since 2025-04-23
 */
@Tag(name = "操作日志管理", description = "操作日志的查询")
@RestController
@RequestMapping("/oper-log")
public class OperLogController {

    @Autowired
    private OperLogService operLogService;

    @Autowired
    private OperLogConvert operLogConvert;

    @Operation(summary = "操作日志分页")
    @GetMapping
    public Result<PageData<OperLogDTO>> page(
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize") Integer pageSize) {
        OperLogCondition condition = new OperLogCondition(pageNum, pageSize);
        PageData<OperLogModel> page = operLogService.pageByCondition(condition);
        return Result.success(PageUtil.makeResponse(page, operLogConvert::model2Dto));
    }

    @Operation(summary = "操作日志详情")
    @GetMapping("/{logId}")
    public Result<OperLogDTO> detail(@PathVariable("logId") Long logId) {
        OperLogModel operLogModel = operLogService.getByLogId(logId);
        return Result.success(operLogModel, operLogConvert::model2Dto);
    }

}
