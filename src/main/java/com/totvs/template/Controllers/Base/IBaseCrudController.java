package com.totvs.template.Controllers.Base;

import com.totvs.template.Domain.Dto.Security.Users.UserListDto;
import com.totvs.tjf.api.context.v1.request.ApiFieldRequest;
import com.totvs.tjf.api.context.v1.request.ApiPageRequest;
import com.totvs.tjf.api.context.v1.request.ApiSortRequest;
import com.totvs.tjf.api.context.v1.response.ApiCollectionResponse;

public interface IBaseCrudController<TEntity, TCreateDto, TListDto, TEntityDto> {

    ApiCollectionResponse<TListDto> getAll(ApiFieldRequest field, ApiPageRequest page, ApiSortRequest sort);

    TEntity get(Long id);

    TEntity create(TCreateDto request);

    TEntity update(TEntity request);

    void delete(Long id);
}
