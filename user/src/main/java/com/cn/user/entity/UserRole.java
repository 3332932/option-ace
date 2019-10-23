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
 * 用户与权限关系表
 * </p>
 *
 * @author ms.x
 * @since 2019-09-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("用户与权限关系表")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableField("user_id")
    @ApiModelProperty(value = "用户id")
    private Integer userId;
    /**
     * 权限id
     */
    @TableField("role_id")
    @ApiModelProperty(value = "权限id")
    private String roleId;


}
