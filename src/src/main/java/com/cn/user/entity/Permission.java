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
 * 
 * </p>
 *
 * @author ms.x
 * @since 2019-09-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "permission_id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Integer permissionId;
    @TableField("permission_value")
    @ApiModelProperty(value = "")
    private String permissionValue;
    @TableField("permission_name")
    @ApiModelProperty(value = "")
    private String permissionName;
    @TableField("description")
    @ApiModelProperty(value = "")
    private String description;
    @TableField("create_time")
    @ApiModelProperty(value = "")
    private LocalDateTime createTime;
    @TableField("creator")
    @ApiModelProperty(value = "")
    private String creator;
    @TableField("modifier")
    @ApiModelProperty(value = "")
    private String modifier;
    @TableField("modify_time")
    @ApiModelProperty(value = "")
    private LocalDateTime modifyTime;


}
