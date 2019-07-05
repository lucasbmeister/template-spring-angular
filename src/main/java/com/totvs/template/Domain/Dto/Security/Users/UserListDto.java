package com.totvs.template.Domain.Dto.Security.Users;

import com.totvs.template.Domain.Dto.Base.EntityBaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserListDto extends EntityBaseDto {
    // Attributes
    public String username;
    public String mail;
    public String name;
}
