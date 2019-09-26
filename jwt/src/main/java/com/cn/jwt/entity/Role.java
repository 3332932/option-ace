package com.cn.jwt.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yshh44
 */
@Getter
@Setter
public class Role {
    private Long roleId;
    private String roleName;
    private String description;
    private String createTime;
    private String creator;
    private String modifyTime;
    private String modifier;

}
