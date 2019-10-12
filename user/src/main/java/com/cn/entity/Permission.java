package com.cn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 权限
 * </p>
 *
 * @author ms.x
 * @since 2019-09-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("权限")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限id
     */
    @TableId(value = "permission_id", type = IdType.AUTO)
    @ApiModelProperty(value = "权限id")
    private Integer permissionId;
    /**
     * 权限值
     */
    @TableField("permission_value")
    @ApiModelProperty(value = "权限值")
    private String permissionValue;
    /**
     * 权限名称
     */
    @TableField("permission_name")
    @ApiModelProperty(value = "权限名称")
    private String permissionName;
    /**
     * 描述
     */
    @TableField("description")
    @ApiModelProperty(value = "描述")
    private String description;
    /**
     * 创建时间
     */
    @TableField("create_time")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    /**
     * 创建人
     */
    @TableField("creator")
    @ApiModelProperty(value = "创建人")
    private String creator;
    /**
     * 更新人
     */
    @TableField("modifier")
    @ApiModelProperty(value = "更新人")
    private String modifier;
    /**
     * 更新时间
     */
    @TableField("modify_time")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime modifyTime;


}
