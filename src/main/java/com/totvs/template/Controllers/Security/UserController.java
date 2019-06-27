package com.totvs.template.Controllers.Security;

import com.totvs.tjf.api.context.stereotype.ApiGuideline;
import com.totvs.template.Controllers.Base.BaseCrudController;
import com.totvs.template.Domain.Dto.Security.Users.CreateUserDto;
import com.totvs.template.Domain.Entities.Security.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@ApiGuideline(ApiGuideline.ApiGuidelineVersion.v1)
@RequestMapping(path = "${endpoint.api}/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController extends BaseCrudController<User, CreateUserDto, CreateUserDto, CreateUserDto> {
}
