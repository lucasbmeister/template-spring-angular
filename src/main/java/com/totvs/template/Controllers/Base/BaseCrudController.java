package com.totvs.template.Controllers.Base;

import com.google.common.reflect.TypeToken;
import com.totvs.template.Domain.Dto.Base.EntityBaseDto;
import com.totvs.tjf.api.context.v1.request.ApiFieldRequest;
import com.totvs.tjf.api.context.v1.request.ApiPageRequest;
import com.totvs.tjf.api.context.v1.request.ApiSortRequest;
import com.totvs.tjf.api.context.v1.response.ApiCollectionResponse;
import com.totvs.template.Domain.Entities.Base.EntityBase;
import com.totvs.template.Exceptions.Base.EntityNotFoundException;
import com.totvs.template.Services.Base.IBaseCrudService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class BaseCrudController<TEntity extends EntityBase,
                            TCreateDto extends EntityBaseDto,
                            TListDto  extends EntityBaseDto,
                            TEntityDto extends EntityBaseDto>
                            implements IBaseCrudController<TEntity, TCreateDto, TListDto, TEntityDto> {

    @Autowired
    private IBaseCrudService<TEntity> _service;

    @Autowired
    private ModelMapper _modelMapper;

    @Override
    @GetMapping
    public ApiCollectionResponse<TListDto> getAll( ApiFieldRequest field, ApiPageRequest page, ApiSortRequest sort) {
        CompletableFuture<ApiCollectionResponse<TEntity>> listProjected = this._service.findAllProjected(page, sort, field);

        try {
            Type collectionDtoType = new TypeToken<Collection<TListDto>>(getClass()) {}.getType();
            Collection<TListDto> collectionDto = this._modelMapper.map(listProjected.get().getItems(), collectionDtoType);
            ApiCollectionResponse<TListDto> responseList = ApiCollectionResponse.of(collectionDto, this._service.hasMorePages(page).get());
            return responseList;
        }
        catch (Exception e)
        {
           e.printStackTrace();
        }

        return null;
    }

    @Override
    @GetMapping({"/{id}"})
    public TEntity get(@PathVariable(value = "id") Long id){

        try {
            CompletableFuture<Optional<TEntity>> entity = this._service.findOne(id);
            Optional<TEntity> entityOptional = entity.get();

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

    @Override
    @PostMapping
    public TEntity create(@RequestBody TCreateDto request) {
        try {
            Type entityType = new TypeToken<TEntity>(getClass()) {}.getType();
            return this._service.insert(this._modelMapper.map(request, entityType)).get();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @PutMapping
    public TEntity update(@RequestBody TEntity request) {
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

    @Override
    @DeleteMapping({"/{id}"})
    public void delete(@PathVariable(value = "id") Long id) {
        this._service.delete(id);
    }

}
