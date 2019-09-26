package com.cn.jwt.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author yshh44
 */

@Getter
@Setter
public class Permission {
    private Long permissionId;

    private String permissionValue;

    private String permissionName;

    private String description;

    private Date createTime;

    private String creator;

    private Date modifyTime;

    private String modifier;
}
