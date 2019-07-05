package com.totvs.template.Services.Base;

import com.totvs.tjf.api.context.v1.request.ApiFieldRequest;
import com.totvs.tjf.api.context.v1.request.ApiPageRequest;
import com.totvs.tjf.api.context.v1.request.ApiSortRequest;
import com.totvs.tjf.api.context.v1.response.ApiCollectionResponse;

import java.util.Collection;
import java.util.Optional;

public interface IBaseCrudService<TEntity> {

    TEntity insert(TEntity entity);

    TEntity update(TEntity entity);

    void delete(Long Id);

    void delete(TEntity entity);

    Optional<TEntity> findOne(Long Id);

    ApiCollectionResponse<TEntity> findAllProjected(ApiPageRequest page, ApiSortRequest sort, ApiFieldRequest field);

//    Collection<TEntity> findAll(ApiPageRequest page, ApiSortRequest sort);

    boolean hasMorePages(ApiPageRequest page);




}
