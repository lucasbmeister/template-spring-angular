package com.totvs.template.Controllers.Security;

import com.totvs.template.Controllers.Base.BaseCrudController;
import com.totvs.template.Domain.Dto.Security.Roles.CreateRoleDto;
import com.totvs.template.Domain.Dto.Security.Roles.RoleDto;
import com.totvs.template.Domain.Entities.Security.Role;
import com.totvs.tjf.api.context.stereotype.ApiGuideline;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ApiGuideline(ApiGuideline.ApiGuidelineVersion.v1)
@RequestMapping(path = "${endpoint.api}/roles", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleController extends BaseCrudController<Role, CreateRoleDto, CreateRoleDto, RoleDto> {
}
