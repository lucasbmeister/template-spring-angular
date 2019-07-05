package com.totvs.template.Domain.Dto.Security.Roles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.totvs.template.Annotations.Exclude;
import com.totvs.template.Domain.Dto.Base.EntityBaseDto;
import com.totvs.template.Domain.Dto.Security.Users.UserDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

public class RoleDto extends EntityBaseDto {

    @Getter @Setter @Exclude @JsonIgnore
    private Set<UserDto> user;
    @Getter @Setter
    private String role;
}
