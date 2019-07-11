package com.totvs.template.Domain.Dto.Security.Roles;

import com.totvs.template.Domain.Dto.Base.EntityBaseDto;
import lombok.Getter;
import lombok.Setter;

public class RoleDto extends EntityBaseDto {

//    @Getter @Setter @Exclude @JsonIgnore
//    private Set<UserDto> users;
    @Getter @Setter
    private String role;
}
