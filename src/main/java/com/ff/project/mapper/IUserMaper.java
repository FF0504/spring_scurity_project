package com.ff.project.mapper;

import com.ff.project.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: FF
 * @Date: 2019/6/17 19:27
 * @Version 1.0
 */
@Mapper
public interface IUserMaper {
    public User findByUserName(String username);
}
