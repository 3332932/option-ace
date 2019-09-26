package com.cn.shrio.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author 'ms.x'
 * @date 2017/7/21
 */
@TableName("role_permission")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RolePermission implements Serializable{

	@TableField("role_id")
	private Long roleId;
	@TableField("permission_id")
	private Long permissionId;

}
