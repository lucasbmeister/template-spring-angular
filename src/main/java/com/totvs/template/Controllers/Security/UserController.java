package com.totvs.template.Controllers.Security;

import com.totvs.template.Domain.Dto.Security.Users.UserDto;
import com.totvs.template.Domain.Dto.Security.Users.UserListDto;
import com.totvs.tjf.api.context.stereotype.ApiGuideline;
import com.totvs.template.Controllers.Base.BaseCrudController;
import com.totvs.template.Domain.Entities.Security.User;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@ApiGuideline(ApiGuideline.ApiGuidelineVersion.v1)
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping(path = "${endpoint.api}/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController extends BaseCrudController<User, UserDto, UserListDto, UserDto> {

}
