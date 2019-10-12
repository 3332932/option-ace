package com.cn.dynamic.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author ms.x
 * @since 2019-10-09
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("")
public class DmExpressB30 implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 月份
     */
    @TableField("YEAR_MONTHS")
    @ApiModelProperty(value = "月份")
    private String yearMonths;
    /**
     * 承运商编号
     */
    @TableField("CARRIER_NO")
    @ApiModelProperty(value = "承运商编号")
    private String carrierNo;
    /**
     * 承运商名称
     */
    @TableField("CARRIER_NAME")
    @ApiModelProperty(value = "承运商名称")
    private String carrierName;
    /**
     * 系统仓编号
     */
    @TableField("DELIVERY_LOCNO")
    @ApiModelProperty(value = "系统仓编号")
    private String deliveryLocno;
    /**
     * 系统仓名称
     */
    @TableField("DELIVER_LOCNAME")
    @ApiModelProperty(value = "系统仓名称")
    private String deliverLocname;
    /**
     * 实体仓
     */
    @TableField("LOCNO_NAME")
    @ApiModelProperty(value = "实体仓")
    private String locnoName;
    /**
     * 三级来源
     */
    @TableField("ORDER_FORM")
    @ApiModelProperty(value = "三级来源")
    private String orderForm;
    /**
     * 品牌
     */
    @TableField("SYS_NO")
    @ApiModelProperty(value = "品牌")
    private String sysNo;
    /**
     * 件数
     */
    @TableField("T_NUMBER")
    @ApiModelProperty(value = "件数")
    private String tNumber;
    /**
     * 金额
     */
    @TableField("AMOUNT")
    @ApiModelProperty(value = "金额")
    private String amount;


}
