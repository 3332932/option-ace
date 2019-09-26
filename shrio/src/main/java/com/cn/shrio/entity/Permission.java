package com.cn.shrio.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author yshh44
 */
@Data
@TableName("permission")
public class Permission {
    @TableField("permission_id")
    private Long permissionId;

    @TableField("permission_value")
    private String permissionValue;

    @TableField("permission_name")
    private String permissionName;

    @TableField("description")
    private String description;

    @TableField("create_time")
    private Date createTime;

    @TableField("creator")
    private String creator;

    @TableField("modify_time")
    private Date modifyTime;

    @TableField("modifier")
    private String modifier;
}
