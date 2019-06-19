package com.ff.project.secutity;

import com.ff.project.domain.Permission;
import com.ff.project.mapper.IPermissionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Author: FF
 * @Date: 2019/6/19 9:36
 * @Version 1.0 拦截url所需的全部权限
 */
@Service
public class MyCutomerInvocationMetadataSourceService  implements FilterInvocationSecurityMetadataSource{

    private Logger logger= LoggerFactory.getLogger(MyCutomerInvocationMetadataSourceService.class);

    @Autowired
    private IPermissionMapper permissionMapper;

    /**
     * 加载资源，初始化资源变量
     *
     */
    //加载资源 todo 可以优化放到redis里面
    public HashMap<String, Collection<ConfigAttribute>> loadResourceDefine(){
        HashMap<String, Collection<ConfigAttribute>>  map = new HashMap<>();
        Collection<ConfigAttribute> array;
        ConfigAttribute cfg;
        List<Permission> permissions = permissionMapper.findAll();
        for(Permission permission : permissions) {
            array = new ArrayList<>();
            cfg = new SecurityConfig(permission.getName());
            array.add(cfg);
            map.put(permission.getUrl(), array);
        }
        return map;

    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        logger.info("访问的url信息:"+((FilterInvocation) object).getRequestUrl());
        HashMap<String, Collection<ConfigAttribute>> map = loadResourceDefine();
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        AntPathRequestMatcher matcher;
        String resUrl;
        for(Iterator<String> iter = map.keySet().iterator(); iter.hasNext(); ) {
            resUrl = iter.next();
            matcher = new AntPathRequestMatcher(resUrl);
            if(matcher.matches(request)) {
                return map.get(resUrl);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
