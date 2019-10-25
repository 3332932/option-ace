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
 * @since 2019-10-24
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("")
public class EasyRptExport implements Serializable {

    private static final long serialVersionUID = 1L;

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
    @TableField("rpt_name")
    @ApiModelProperty(value = "")
    private String rptName;
    @TableField("rpt_url")
    @ApiModelProperty(value = "")
    private String rptUrl;
    @TableField("select_sql")
    @ApiModelProperty(value = "")
    private String selectSql;
    @TableField("sql_group_fields")
    @ApiModelProperty(value = "")
    private String sqlGroupFields;
    @TableField("remarks")
    @ApiModelProperty(value = "")
    private String remarks;
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
    @TableField("del_tag")
    @ApiModelProperty(value = "")
    private String delTag;
    @TableField("update_time")
    @ApiModelProperty(value = "")
    private LocalDateTime updateTime;


}
