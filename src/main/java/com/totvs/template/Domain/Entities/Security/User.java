package com.totvs.template.Domain.Entities.Security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.totvs.template.Annotations.Exclude;
import com.totvs.template.Domain.Entities.Base.EntityBase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Table(name = "app_user")
@NoArgsConstructor
public class User extends EntityBase {

	public User(User user){
		setUsername(user.getUsername());
		setName(user.getName());
		setRoles(user.getRoles());
		setMail(user.getMail());
		setSurname(user.getSurname());
	}

	// Attributes
	@NotNull
	@Getter @Setter
	private String username;
	@NotNull
	@Getter @Setter
    private String password;
	@Getter @Setter
    private String token;

	@ManyToMany(fetch = FetchType.EAGER,
			cascade = {
					CascadeType.MERGE
					//CascadeType.PERSIST
			})
	@JoinTable(name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)

	@Getter @Setter
    private Set<Role> roles = new HashSet<>();

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