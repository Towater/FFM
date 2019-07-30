package com.ffs.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
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
public class BankAccountInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 银行账户
     */
    @TableField("accountNumber")
    private String accountNumber;

    /**
     * 开户行
     */
    @TableField("openBank")
    private String openBank;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 用户id
     */
    @TableField("userId")
    private Long userId;


}
