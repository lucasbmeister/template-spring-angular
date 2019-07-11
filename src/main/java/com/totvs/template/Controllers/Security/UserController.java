package com.totvs.template.Controllers.Security;

import com.google.common.reflect.TypeToken;
import com.totvs.template.Domain.Dto.Security.Users.UserDto;
import com.totvs.template.Domain.Dto.Security.Users.UserListDto;
import com.totvs.template.Exceptions.Base.EntityNotFoundException;
import com.totvs.template.Services.Security.UserService;
import com.totvs.tjf.api.context.stereotype.ApiGuideline;
import com.totvs.template.Domain.Entities.Security.User;
import com.totvs.tjf.api.context.v1.request.ApiFieldRequest;
import com.totvs.tjf.api.context.v1.request.ApiPageRequest;
import com.totvs.tjf.api.context.v1.request.ApiSortRequest;
import com.totvs.tjf.api.context.v1.response.ApiCollectionResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@ApiGuideline(ApiGuideline.ApiGuidelineVersion.v1)
@RequestMapping(path = "${endpoint.api}/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService _service;

    @Autowired
    private ModelMapper _modelMapper;

    @GetMapping
    public ApiCollectionResponse<UserListDto> getAll(ApiFieldRequest field, ApiPageRequest page, ApiSortRequest sort) {
        CompletableFuture<ApiCollectionResponse<User>> listProjected = this._service.findAllProjected(page, sort, field);

        try {
            Type collectionDtoType = new TypeToken<Collection<UserListDto>>(getClass()) {}.getType();
            Collection<UserListDto> collectionDto = this._modelMapper.map(listProjected.get().getItems(), collectionDtoType);
            ApiCollectionResponse<UserListDto> responseList = ApiCollectionResponse.of(collectionDto, this._service.hasMorePages(page).get());
            return responseList;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @GetMapping({"/{id}"})
    public User get(@PathVariable(value = "id") Long id){

        try {
            CompletableFuture<Optional<User>> entity = this._service.findOne(id);
            Optional<User> entityOptional = entity.get();

            if (entityOptional.isPresent()) {
                return entityOptional.get();
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

    @PostMapping
    public User create(@RequestBody UserDto request) {
        try {
            Type entityType = new TypeToken<User>(getClass()) {}.getType();
            return this._service.insert(this._modelMapper.map(request, entityType)).get();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping
    public User update(@RequestBody User request) {
        try
        {
            return this._service.update(request).get();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping({"/{id}"})
    public void delete(@PathVariable(value = "id") Long id) {
        this._service.delete(id);
    }
}
