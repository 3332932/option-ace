package com.cn.user.entity;

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
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("role_id")
    @ApiModelProperty(value = "")
    private Integer roleId;
    @TableField("permission_id")
    @ApiModelProperty(value = "")
    private String permissionId;


}
