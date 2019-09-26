package com.cn.shrio.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author yshh44
 */
@TableName("role")
@Data
public class Role {
    @TableField("role_id")
    private Long roleId;
    @TableField("role_name")
    private String roleName;
    @TableField("description")
    private String description;
    @TableField("create_time")
    private String createTime;
    @TableField("creator")
    private String creator;
    @TableField("modify_time")
    private String modifyTime;
    @TableField("modifier")
    private String modifier;

}
