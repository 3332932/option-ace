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
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Integer userId;
    @TableField("user_name")
    @ApiModelProperty(value = "")
    private String userName;
    @TableField("password")
    @ApiModelProperty(value = "")
    private String password;
    @TableField("nick_name")
    @ApiModelProperty(value = "")
    private String nickName;
    @TableField("avatar")
    @ApiModelProperty(value = "")
    private String avatar;
    @TableField("level")
    @ApiModelProperty(value = "")
    private String level;
    @TableField("salt")
    @ApiModelProperty(value = "")
    private String salt;
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
