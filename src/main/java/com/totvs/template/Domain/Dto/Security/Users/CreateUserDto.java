package com.totvs.template.Domain.Dto.Security.Users;

import com.totvs.template.Domain.Dto.Base.EntityBaseDto;
import lombok.*;

@Getter
@Setter
public class CreateUserDto extends EntityBaseDto {

    public String username;

    public String password;
}
