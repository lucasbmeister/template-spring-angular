package com.totvs.template.Services.Security;

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

    public Role findByRoleAndUser(String role, User user) {
    	return rolesRepository.findByRoleAndUser(role, user);
    }

    public List<Role> findByUser(User user) {
		return rolesRepository.findByUser(user);
	}

//    public void delete(Long role) {
//    	Role roleToDelete = rolesRepository.getOne(role);
//	    rolesRepository.delete(roleToDelete);
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