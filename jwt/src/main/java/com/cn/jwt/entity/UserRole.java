package com.cn.jwt.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 *
 * @author 'ms.x'
 * @date 2017/7/21
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRole implements Serializable{
	/**
	 * 用户id
	 */
	private Long userId;
	private Long roleId;

}
