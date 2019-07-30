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
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.format.annotation.DateTimeFormat;

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
public class IncomeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 金额大小
     */
    private BigDecimal quota;

    /**
     * 收入类型
     */
    private String type;

    /**
     * 收入者
     */
    private Long userid;

    /**
     * 收入日期
     */
    @JsonFormat(pattern = "yyyy/MM/dd",timezone = "GMT+8")//考虑到是记录自己的收入状况，取消时分秒的记录
    @TableField("incomeDate")
    private Date incomeDate;

    /**
     * 收入源
     */
    @TableField("incomeRoot")
    private String incomeRoot;

    /**
     * 相关备注
     */
    private String remakers;


}
