package com.ff.project.domain;

import lombok.Data;

import java.util.List;

/**
 * @Author: FF
 * @Date: 2019/6/17 18:47
 * @Version 1.0
 */
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private List<Role> roles;
    private List<Permission> permissionList;
}
