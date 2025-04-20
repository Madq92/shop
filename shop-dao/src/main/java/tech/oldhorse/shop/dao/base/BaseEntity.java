package tech.oldhorse.shop.dao.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BaseEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 删除表示：0-未删除，1-已删除
     */
    private Boolean deletedFlag;

    /**
     * 服务器创建时间
     */
    private LocalDateTime createTime;

    /**
     * 服务器更新时间
     */
    private LocalDateTime updateTime;
}
