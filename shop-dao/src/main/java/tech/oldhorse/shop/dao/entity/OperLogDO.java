package tech.oldhorse.shop.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * <p>
 * 操作日志记录
 * </p>
 *
 * @author mika
 * @since 2025-04-23
 */
@Getter
@Setter
@ToString
@TableName("oper_log")
public class OperLogDO {

    /**
     * 日志主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 模块标题
     */
    private String title;

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    private Integer businessType;

    /**
     * 方法名称
     */
    private String method;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 链路ID
     */
    private String traceId;

    /**
     * 操作类别（0其它 1后台用户 2手机端用户）
     */
    private Integer operatorType;

    /**
     * 操作用户ID
     */
    private String operUserId;

    /**
     * 操作用户名称
     */
    private String operUseName;

    /**
     * 请求URL
     */
    private String operUrl;

    /**
     * 主机地址
     */
    private String operIp;

    /**
     * 操作地点
     */
    private String operLocation;

    /**
     * 请求参数
     */
    private String operParam;

    /**
     * 返回参数
     */
    private String jsonResult;

    /**
     * 成功标识（1成功 0失败）
     */
    private Byte success;

    /**
     * 错误消息
     */
    private String errorMsg;

    /**
     * 操作时间
     */
    private LocalDateTime operTime;

    /**
     * 消耗时间
     */
    private Long costTime;

    /**
     * 租户ID
     */
    private String tenantId;
}
