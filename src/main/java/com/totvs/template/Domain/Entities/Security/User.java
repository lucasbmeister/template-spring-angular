package com.totvs.template.Domain.Entities.Security;

import com.totvs.template.Domain.Entities.Base.EntityBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name = "app_user")
public class User extends EntityBase {

	// Attributes
	@NotNull
	@Getter @Setter
	private String username;
	@NotNull
	@Getter @Setter
    private String password;
	@Getter @Setter
    private String token;
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	@Getter @Setter
    private List<Role> roles = new ArrayList<>();;
	@Getter @Setter
    private String mail;
	@Getter @Setter
    private String name;
	@Getter @Setter
    private String surname;


	public void addRoles(Role role) {
		this.roles.add(role);
	}

	public boolean hasRole(String role) {
		List<String> lista = roles.stream()
				.map(Role::getRole)
				.collect(Collectors.toList());
		return lista.indexOf(role) != -1;
	}

}