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
@TableName("user_role")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRole implements Serializable{
	/**
	 * 用户id
	 */
	@TableField("user_id")
	private Long userId;
	@TableField("role_id")
	private Long roleId;

}
