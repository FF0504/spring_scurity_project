package com.ff.project.domain;

import lombok.Data;

/**
 * @Author: FF
 * @Date: 2019/6/17 18:50
 * @Version 1.0
 */
@Data
public class PermissionRole {
    private Integer id;
    private Integer permissionId;
    private Integer sysRoleId;
}
