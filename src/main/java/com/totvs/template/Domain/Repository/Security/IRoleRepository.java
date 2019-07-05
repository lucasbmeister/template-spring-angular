package com.totvs.template.Domain.Repository.Security;

import com.totvs.template.Domain.Entities.Security.Role;
import com.totvs.template.Domain.Entities.Security.User;
import com.totvs.template.Domain.Repository.Base.IBaseRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface IRoleRepository extends IBaseRepository<Role> {
    //Role findByRoleAndUser(String role, User user);
    //List<Role> findByUser(User users);
}
