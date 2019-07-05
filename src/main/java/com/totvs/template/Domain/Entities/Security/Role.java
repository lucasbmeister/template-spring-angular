package com.totvs.template.Domain.Entities.Security;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.totvs.template.Annotations.Exclude;
import com.totvs.template.Domain.Entities.Base.EntityBase;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
public class Role extends EntityBase {

	@ManyToMany(mappedBy = "roles")
	@Exclude @JsonIgnore
	private Set<User> users = new HashSet<>();

	private String role;

	public Role() {}

	public Role(String role) {
		this.role = role;
	}
}
