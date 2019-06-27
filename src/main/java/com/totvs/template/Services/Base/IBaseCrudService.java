package com.totvs.template.Services.Base;

import com.totvs.tjf.api.context.v1.request.ApiFieldRequest;
import com.totvs.tjf.api.context.v1.request.ApiPageRequest;
import com.totvs.tjf.api.context.v1.request.ApiSortRequest;
import com.totvs.tjf.api.context.v1.response.ApiCollectionResponse;

import java.util.Optional;

public interface IBaseCrudService<TEntity> {

    TEntity Insert(TEntity entity);

    TEntity Update(TEntity entity);

    void Delete(Long Id);

    void Delete(TEntity entity);

    Optional<TEntity> FindOne(Long Id);

    ApiCollectionResponse<TEntity> findAllProjected(ApiFieldRequest field, ApiPageRequest page, ApiSortRequest sort);




}
