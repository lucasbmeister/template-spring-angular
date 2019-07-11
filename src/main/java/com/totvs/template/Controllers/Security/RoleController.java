package com.totvs.template.Controllers.Security;

import com.google.common.reflect.TypeToken;
import com.totvs.template.Domain.Dto.Security.Roles.RoleDto;
import com.totvs.template.Domain.Entities.Security.Role;
import com.totvs.template.Exceptions.Base.EntityNotFoundException;
import com.totvs.template.Services.Security.RolesService;
import com.totvs.tjf.api.context.stereotype.ApiGuideline;
import com.totvs.tjf.api.context.v1.request.ApiFieldRequest;
import com.totvs.tjf.api.context.v1.request.ApiPageRequest;
import com.totvs.tjf.api.context.v1.request.ApiSortRequest;
import com.totvs.tjf.api.context.v1.response.ApiCollectionResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@ApiGuideline(ApiGuideline.ApiGuidelineVersion.v1)
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping(path = "${endpoint.api}/roles", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleController {

    @Autowired
    private RolesService _rolesService;

    @Autowired
    private ModelMapper _modelMapper;

    @GetMapping
    public ApiCollectionResponse<RoleDto> getAll(ApiPageRequest page, ApiSortRequest sort, ApiFieldRequest field) {
        CompletableFuture<ApiCollectionResponse<Role>> listProjected = this._rolesService.findAllProjected(page, sort, field);

        try {
            Type collectionDtoType = new TypeToken<Collection<RoleDto>>(getClass()) {}.getType();
            Collection<RoleDto> collectionDto = this._modelMapper.map(listProjected.get().getItems(), collectionDtoType);
            ApiCollectionResponse<RoleDto> responseList = ApiCollectionResponse.of(collectionDto, this._rolesService.hasMorePages(page).get());
            return responseList;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping({"/{id}"})
    public Role get(@PathVariable(value = "id") Long id) {

        try {
            CompletableFuture<Optional<Role>> entity = this._rolesService.findOne(id);
            Optional<Role> roleOptional = entity.get();
            if (roleOptional.isPresent()) {
                return roleOptional.get();
            } else {
                throw new EntityNotFoundException("Não Encontrado");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
