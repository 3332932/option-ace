package com.cn.jwt.entity;

import com.cn.jwt.annotation.JwtConfig;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author 'ms.x'
 * @date 2017/7/21
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable{
	/**
	 * 用户id
	 */
	@JwtConfig
	private Long userId;
	/**
	 * 用户名
	 */
	@JwtConfig
	private String username;
	/**
	 * 用户密码
	 */
	private String password;
	/**
	 * 昵称
	 */
	@JwtConfig
	private String nickName;
	/**
	 * 头像
	 */
	@JwtConfig
	private String avatar;
	/**
	 * vip级别
	 */
	@JwtConfig
	private Integer level;
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	private Date createTime;
	/**
	 * 最后更新时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	private Date updateTime;
	private String salt;
	@JwtConfig
	private Set<String> role;

}
