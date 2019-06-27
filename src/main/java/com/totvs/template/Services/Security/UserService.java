package com.totvs.template.Services.Security;

import java.util.ArrayList;
import java.util.List;

import com.totvs.template.Domain.Entities.Security.Role;
import com.totvs.template.Domain.Entities.Security.User;
import com.totvs.template.Domain.Repository.Security.IRoleRepository;
import com.totvs.template.Domain.Repository.Security.IUserRepository;
import com.totvs.template.Services.Base.BaseCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService extends BaseCrudService<User> {

	
	@Autowired
	IUserRepository userRepository;
	@Autowired
	IRoleRepository rolesRepository;
	private static RolesService rolesService = new RolesService();

	public User login(String username, String password) {
		if (userRepository.count() == 0) {
			User newUser = new User();
			newUser.setUsername("admin");
			newUser.addRoles(new Role("ADMIN", newUser));
			newUser.setPassword("62f264d7ad826f02a8af714c0a54b197935b717656b80461686d450f7b3abde4c553541515de2052b9af70f710f0cd8a1a2d3f4d60aa72608d71a63a9a93c0f5");
			rolesRepository.save(newUser.getRoles().get(0));
			userRepository.save(newUser);
		}
		return userRepository.findByUsernameAndPassword(username, password);
	}
    
    //CRUD - CREATE
    
	public User insert(User user) {
		return userRepository.save(user);
	}
	
	//CRUD - REMOVE
    
	public void delete(Long id) {
		User user = userRepository.getOne(id);
		userRepository.delete(user);
	}

	//CRUD - GET ONE
    	
	public User getOne(Long id) {
		return userRepository.getOne(id);
	}

    	
    //CRUD - GET LIST
    	
	public List<User> getAll() {
		List<User> list = new ArrayList<>();
		userRepository.findAll().forEach(list::add);
		return list;
	}
	

}
