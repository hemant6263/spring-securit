package com.assigment.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.assigment.security.entity.Role;
import com.assigment.security.entity.User;

public class CustomUserDetail implements UserDetails {
	private User user;
	public CustomUserDetail(User user2) {
		this.user = user2;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<Role> roles = user.getRoles();
		  List<GrantedAuthority> authorities   = new ArrayList();
		  for (Role role: roles) {
		        authorities.add(new SimpleGrantedAuthority(role.getName()));
		        role.getPrivileges().stream()
		         .map(p -> new SimpleGrantedAuthority(p.getName()))
		         .forEach(authorities::add);
		    }
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
