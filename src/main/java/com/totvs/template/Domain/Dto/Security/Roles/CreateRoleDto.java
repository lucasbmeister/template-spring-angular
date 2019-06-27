package com.totvs.template.Domain.Dto.Security.Roles;

import com.totvs.template.Domain.Dto.Base.EntityBaseDto;
import lombok.Getter;
import lombok.Setter;

public class CreateRoleDto extends EntityBaseDto {

    @Getter @Setter
    public String role;
}
