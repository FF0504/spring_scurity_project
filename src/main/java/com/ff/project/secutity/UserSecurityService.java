package com.ff.project.secutity;

import com.ff.project.domain.Permission;
import com.ff.project.domain.Role;
import com.ff.project.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @Author: FF
 * @Date: 2019/6/17 19:41
 * @Version 1.0
 */
public class UserSecurityService extends User implements UserDetails {

    private static final long serialVersionUID = 1L;

    public UserSecurityService(User user) {
        if (user != null) {
            this.setId(user.getId());
            this.setUsername(user.getUsername());
            this.setPassword(user.getPassword());
            this.setRoles(user.getRoles());
           // this.setPermissionList(user.getPermissionList());
        }
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      Collection<GrantedAuthority> authorities = new ArrayList<>();
        for(Role role:this.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
