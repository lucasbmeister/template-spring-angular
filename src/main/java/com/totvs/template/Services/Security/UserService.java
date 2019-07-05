package com.totvs.template.Services.Security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	public boolean isFirstStart(){
		return this.userRepository.count() == 0 ? true : false;
	}

	public User login(String username, String password) {
		if (userRepository.count() == 0) {
			User newUser = new User();
			newUser.setUsername("admin");
			newUser.setName("Administrador");
			newUser.setMail("admin@fsw.com.br");
			newUser.addRoles(new Role("ADMIN"));
			newUser.setPassword("0510c3a26ff918756d5fffb87df4820211a45f294b06732ec339665d09def878d6d963673547166d575393001a96c692a855c309ed1cfb39b1f2088965fd9fd1");
			//rolesRepository.save(newUser.getRoles().get(0));
			userRepository.save(newUser);
		} else {

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
    	
	public Optional<User> findOne(Long id) {
		return userRepository.findById(id);
	}

    	
    //CRUD - GET LIST
    	
	public List<User> getAll() {
		List<User> list = new ArrayList<>();
		userRepository.findAll().forEach(list::add);
		return list;
	}
	

}
