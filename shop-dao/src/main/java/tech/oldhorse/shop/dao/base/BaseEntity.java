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
     * 服务器创建时间 : 服务器创建时间
     */
    private LocalDateTime deleteTime;

    /**
     * 服务器创建时间 : 服务器创建时间
     */
    private LocalDateTime serverCreateTime;

    /**
     * 服务器更新时间 : 服务器更新时间
     */
    private LocalDateTime serverUpdateTime;
}
