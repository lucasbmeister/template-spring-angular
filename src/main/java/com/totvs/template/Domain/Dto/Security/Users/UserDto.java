package com.totvs.template.Domain.Dto.Security.Users;

import com.totvs.template.Domain.Dto.Base.EntityBaseDto;
import com.totvs.template.Domain.Dto.Security.Roles.RoleDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDto extends EntityBaseDto {
	
	// Attributes
	private String username;
    private String password;
    private String token;
    private List<String> roles = new ArrayList<>();
    private String mail;
    private String name;
    private String surname;

	
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
	public List<String> getRoles() {
		return roles;
	}


	public void setRoles(List<RoleDto> roles) {
		this.roles = roles.stream().map(el -> el.getRole()).collect(Collectors.toList());
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