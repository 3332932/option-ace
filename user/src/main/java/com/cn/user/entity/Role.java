package com.cn.user.entity;

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
 * 角色表
 * </p>
 *
 * @author ms.x
 * @since 2019-09-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("角色表")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    @ApiModelProperty(value = "角色id")
    private Integer roleId;
    /**
     * 角色名称
     */
    @TableField("role_name")
    @ApiModelProperty(value = "角色名称")
    private String roleName;
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
