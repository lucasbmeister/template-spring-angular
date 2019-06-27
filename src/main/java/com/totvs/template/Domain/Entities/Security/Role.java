package com.totvs.template.Domain.Entities.Security;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;

import com.totvs.template.Domain.Entities.Base.EntityBase;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Role extends EntityBase {

	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	private String role;

	public Role() {}
	
	public Role(String role, User user) {
		this.role = role;
		this.user = user;
	}

	public Role(String role) {
		this.role = role;
	}
}
