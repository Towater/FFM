package com.ffs.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2019-07-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PayInfo implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 金额大小
     */
    private BigDecimal quota;

    /**
     * 类型
     */
    private String type;

    /**
     * 支出者
     */
    private Long userid;

    /**
     * 支出日期
     */
    @TableField("payDate")
    @JsonFormat(pattern = "yyyy/MM/dd",timezone = "GMT+8")
    private Date payDate;

    /**
     * 支出用途
     */
    @TableField("payFor")
    private String payFor;

    /**
     * 相关备注
     */
    private String remakers;


}
