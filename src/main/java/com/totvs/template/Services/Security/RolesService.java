package com.totvs.template.Services.Security;

import java.util.ArrayList;
import java.util.List;

import com.totvs.template.Domain.Entities.Security.Role;
import com.totvs.template.Domain.Entities.Security.User;
import com.totvs.template.Domain.Repository.Security.IRoleRepository;
import com.totvs.template.Services.Base.BaseCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesService extends BaseCrudService<Role> {
	
	@Autowired
    IRoleRepository rolesRepository;

	public void bootstrap() {
	    this.insert(new Role("ADMIN"));
	    this.insert(new Role("PRIVATE_USER"));
    }

    public Role findByRole(String role){
		return this.rolesRepository.findByRole(role);
	}

//    public void delete(Long role) {
//    	Role roleToDelete = rolesRepository.getOne(role);
//	    rolesRepository.delete(roleToDelete);
//    }

//    public Role findByRoleAndUser(String role, User user) {
//    	return rolesRepository.findByRoleAndUser(role, user);
//    }

//	public List<Role> getRoles(Long id_user) {
//		return null;
//	}
//
//
//    public Role insert(Role role) {
//        return rolesRepository.save(role);
//    }

}