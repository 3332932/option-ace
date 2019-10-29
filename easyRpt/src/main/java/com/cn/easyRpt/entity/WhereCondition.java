package com.cn.easyRpt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
 * @since 2019-10-25
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("")
public class WhereCondition implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String RPT_NO = "rpt_no";

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Integer id;
    @TableField("company_id")
    @ApiModelProperty(value = "")
    private String companyId;
    @TableField("rpt_no")
    @ApiModelProperty(value = "")
    private String rptNo;
    @TableField("where_key")
    @ApiModelProperty(value = "")
    private String whereKey;
    @TableField("where_operator")
    @ApiModelProperty(value = "")
    private String whereOperator;
    @TableField("where_replace_type")
    @ApiModelProperty(value = "")
    private String whereReplaceType;
    @TableField("where_value")
    @ApiModelProperty(value = "")
    private String whereValue;
    @TableField("where_type")
    @ApiModelProperty(value = "")
    private String whereType;
    @TableField("where_type_map")
    @ApiModelProperty(value = "")
    private String whereTypeMap;
    @TableField("where_type_data")
    @ApiModelProperty(value = "")
    private String whereTypeData;
    @TableField("creator")
    @ApiModelProperty(value = "")
    private String creator;
    @TableField("create_time")
    @ApiModelProperty(value = "")
    private LocalDateTime createTime;
    @TableField("modifier")
    @ApiModelProperty(value = "")
    private String modifier;
    @TableField("modify_time")
    @ApiModelProperty(value = "")
    private LocalDateTime modifyTime;
    @TableField("status")
    @ApiModelProperty(value = "")
    private Integer status;
    @TableField("column_width")
    @ApiModelProperty(value = "")
    private Integer columnWidth;

}
