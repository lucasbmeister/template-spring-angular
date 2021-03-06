package com.totvs.template.Security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthUser implements UserDetails{
	
	private static final long serialVersionUID = 2061709915172252885L;
	private long userId;
	private String username;
	private Collection<? extends GrantedAuthority> roles;
	
	public AuthUser (long userId, String username, Collection<? extends GrantedAuthority> roles) {
		this.userId = userId;
		this.username = username;
		this.roles = roles;
	}
	

	public long getUserId() {
		return userId;
	}

	@Override
    public String getUsername() {
        return username;
    }
    
    @Override
    public String getPassword() {
        return "";
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
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.roles;
	}


	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}