package com.ffs.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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
public class Tiger implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 老虎账号
     */
    @TableField("tigerAccount")
    private String tigerAccount;

    /**
     * 用户id
     */
    private Long userid;

    /**
     * 公钥
     */
    @TableField("publicKey")
    private String publicKey;

    /**
     * 私钥
     */
    @TableField("privateKey")
    private String privateKey;


}
