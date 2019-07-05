package com.totvs.template.Domain.Dto.Security.Users;

import com.totvs.template.Domain.Dto.Base.EntityBaseDto;
import com.totvs.template.Domain.Dto.Security.Roles.RoleDto;
import java.util.HashSet;
import java.util.Set;

public class UserDto extends EntityBaseDto {
	
	// Attributes
	public String username;
	public String password;
	public String token;
	public String mail;
	public String name;
	public String surname;
	public Set<RoleDto> roles = new HashSet<>();

	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	public Set<RoleDto> getRoles() {
		return roles;
	}


	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}
	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

}