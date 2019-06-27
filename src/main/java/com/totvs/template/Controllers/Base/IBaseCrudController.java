package com.totvs.template.Controllers.Base;

import com.totvs.tjf.api.context.v1.request.ApiFieldRequest;
import com.totvs.tjf.api.context.v1.request.ApiPageRequest;
import com.totvs.tjf.api.context.v1.request.ApiSortRequest;
import com.totvs.tjf.api.context.v1.response.ApiCollectionResponse;

public interface IBaseCrudController<TEntity, TCreateDto, TListDto, TEntityDto> {

    ApiCollectionResponse<TListDto> GetAll(ApiFieldRequest field, ApiPageRequest page, ApiSortRequest sort);

    TEntity Get(Long id);

    TEntity Create(TCreateDto request);

    TEntity Update(TEntity request);

    void Delete(Long id);
}
