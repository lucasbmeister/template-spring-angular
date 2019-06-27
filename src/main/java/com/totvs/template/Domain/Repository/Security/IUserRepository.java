package com.totvs.template.Domain.Repository.Security;

import com.totvs.template.Domain.Entities.Security.User;
import com.totvs.template.Domain.Repository.Base.IBaseRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface IUserRepository extends IBaseRepository<User> {
    User findByUsernameAndPassword(String username, String password);
}
