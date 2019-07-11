package com.totvs.template.Services.Base;

import com.totvs.tjf.api.context.v1.request.ApiFieldRequest;
import com.totvs.tjf.api.context.v1.request.ApiPageRequest;
import com.totvs.tjf.api.context.v1.request.ApiSortRequest;
import com.totvs.tjf.api.context.v1.response.ApiCollectionResponse;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface IBaseCrudService<TEntity> {

    CompletableFuture<TEntity> insert(TEntity entity);

    CompletableFuture<TEntity> update(TEntity entity);

    void delete(Long Id);

    void delete(TEntity entity);

    CompletableFuture<Optional<TEntity>> findOne(Long Id);

    CompletableFuture<ApiCollectionResponse<TEntity>> findAllProjected(ApiPageRequest page, ApiSortRequest sort, ApiFieldRequest field);

//    Collection<TEntity> findAll(ApiPageRequest page, ApiSortRequest sort);

    CompletableFuture<Boolean> hasMorePages(ApiPageRequest page);




}
