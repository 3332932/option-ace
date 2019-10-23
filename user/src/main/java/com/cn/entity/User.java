package com.cn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author ms.x
 * @since 2019-09-30
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    @ApiModelProperty(value = "用户id")
    private Integer userId;
    /**
     * 用户名称
     */
    @TableField("user_name")
    @ApiModelProperty(value = "用户名称")
    @NotNull(message = "用户名称不能为空")
    private String userName;
    /**
     * 密码
     */
    @NotNull(message = "用户密码不能为空")
    @TableField("password")
    @ApiModelProperty(value = "密码")
    private String password;
    /**
     * 昵称
     */
    @TableField("nick_name")
    @ApiModelProperty(value = "昵称")
    private String nickName;
    /**
     * 头像
     */
    @TableField("avatar")
    @ApiModelProperty(value = "头像")
    private String avatar;
    /**
     * vip等级
     */
    @TableField("level")
    @ApiModelProperty(value = "vip等级")
    private String level;
    /**
     * 盐值
     */
    @TableField("salt")
    @ApiModelProperty(value = "盐值")
    private String salt;
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

    @TableField(exist = false)
    private List<String> roles;

}
