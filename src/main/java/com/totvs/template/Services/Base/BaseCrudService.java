package com.totvs.template.Services.Base;

import com.totvs.tjf.api.context.v1.request.ApiFieldRequest;
import com.totvs.tjf.api.context.v1.request.ApiPageRequest;
import com.totvs.tjf.api.context.v1.request.ApiSortRequest;
import com.totvs.tjf.api.context.v1.response.ApiCollectionResponse;
import com.totvs.template.Domain.Entities.Base.IEntityBase;
import com.totvs.template.Domain.Repository.Base.IBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class BaseCrudService<TEntity extends IEntityBase> implements IBaseCrudService<TEntity> {

    @Autowired
    IBaseRepository<TEntity> _repository;

    @Override
    @Async
    public  CompletableFuture<TEntity> insert(TEntity entity) {
        return CompletableFuture.completedFuture(this._repository.save(entity));
    }

    @Override
    @Async
    public  CompletableFuture<TEntity> update(TEntity entity) {
        return CompletableFuture.completedFuture(this._repository.save(entity));
    }

    @Override
    @Async
    public void delete(Long Id) {
        this._repository.deleteById(Id);
    }

    @Async
    public void delete(TEntity entity) {
        this._repository.delete(entity);
    }

    @Override
    @Async
    public CompletableFuture<Optional<TEntity>> findOne(Long Id) {
        return CompletableFuture.completedFuture(this._repository.findById(Id));
    }

    @Override
    @Async
    public  CompletableFuture<ApiCollectionResponse<TEntity>> findAllProjected(ApiPageRequest page, ApiSortRequest sort, ApiFieldRequest field) {
        return CompletableFuture.completedFuture(this._repository.findAllProjected(field, page, sort));
    }

//    @Override
//    public Collection<TEntity> findAll(ApiPageRequest page, ApiSortRequest sort) {
//        return this._repository.find(page,sort);
//    }

    @Override
    @Async
    public  CompletableFuture<Boolean> hasMorePages(ApiPageRequest page){
        return CompletableFuture.completedFuture(this._repository.hasMorePages(page));
    }
}
