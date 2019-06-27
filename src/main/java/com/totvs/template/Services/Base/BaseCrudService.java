package com.totvs.template.Services.Base;

import com.totvs.tjf.api.context.v1.request.ApiFieldRequest;
import com.totvs.tjf.api.context.v1.request.ApiPageRequest;
import com.totvs.tjf.api.context.v1.request.ApiSortRequest;
import com.totvs.tjf.api.context.v1.response.ApiCollectionResponse;
import com.totvs.template.Domain.Entities.Base.IEntityBase;
import com.totvs.template.Domain.Repository.Base.IBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class BaseCrudService<TEntity extends IEntityBase> implements IBaseCrudService<TEntity> {

    @Autowired
    IBaseRepository<TEntity> _repository;

    @Override
    public TEntity Insert(TEntity entity) {
        return this._repository.save(entity);
    }

    @Override
    public TEntity Update(TEntity entity) {
        return this._repository.save(entity);
    }

    @Override
    public void Delete(Long Id) {
        this._repository.deleteById(Id);
    }

    public void Delete(TEntity entity) {
        this._repository.delete(entity);
    }

    @Override
    public Optional<TEntity> FindOne(Long Id) {
        return this._repository.findById(Id);
    }

    @Override
    public ApiCollectionResponse<TEntity> findAllProjected(ApiFieldRequest field, ApiPageRequest page, ApiSortRequest sort) {
        return this._repository.findAllProjected(field, page, sort);
    }
}
