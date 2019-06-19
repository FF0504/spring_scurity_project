package com.ff.project.mapper;

import com.ff.project.domain.Permission;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * @Author: FF
 * @Date: 2019/6/18 14:57
 * @Version 1.0
 */
@Mapper
public interface IPermissionMapper {
    public List<Permission> findAll();
    public List<Permission> findByAdminUserId(int userId);
}
