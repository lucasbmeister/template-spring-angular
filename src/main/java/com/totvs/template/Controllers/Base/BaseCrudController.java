package com.totvs.template.Controllers.Base;

import com.google.common.reflect.TypeToken;
import com.totvs.tjf.api.context.v1.request.ApiFieldRequest;
import com.totvs.tjf.api.context.v1.request.ApiPageRequest;
import com.totvs.tjf.api.context.v1.request.ApiSortRequest;
import com.totvs.tjf.api.context.v1.response.ApiCollectionResponse;
import com.totvs.template.Domain.Dto.Base.IEntityBaseDto;
import com.totvs.template.Domain.Entities.Base.EntityBase;
import com.totvs.template.Exceptions.Base.EntityNotFoundException;
import com.totvs.template.Services.Base.IBaseCrudService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.Type;
import java.util.Optional;

public class BaseCrudController<TEntity extends EntityBase,
                            TCreateDto extends IEntityBaseDto,
                            TListDto extends IEntityBaseDto,
                            TEntityDto extends IEntityBaseDto>
                            implements IBaseCrudController<TEntity, TCreateDto, TListDto, TEntityDto> {

    @Autowired
    private IBaseCrudService<TEntity> _service;

    @Autowired
    private ModelMapper _modelMapper;

    @Override
    @GetMapping
    public ApiCollectionResponse<TListDto> GetAll( ApiFieldRequest field, ApiPageRequest page, ApiSortRequest sort) {
        ApiCollectionResponse<TEntity> responseList = this._service.findAllProjected(field, page, sort);
        try {
            Type listDtoType = new TypeToken<ApiCollectionResponse<TListDto>>() {}.getType();
            return this._modelMapper.map(responseList, listDtoType);
        }
        catch (Exception e)
        {
           e.printStackTrace();
        }

        return null;
    }

    @Override
    @GetMapping({"/{id}"})
    public TEntity Get(@PathVariable(value = "id") Long id) {
        Optional<TEntity> entity = this._service.FindOne(id);
        if(entity.isPresent()) {
            return entity.get();
        }
        else {
            throw new EntityNotFoundException("Não Encontrado");
        }
    }

    @Override
    @PostMapping
    public TEntity Create(@RequestBody TCreateDto request) {
        try {
            Type entityType = new TypeToken<ApiCollectionResponse<TEntity>>() {}.getType();
            return this._service.Insert(this._modelMapper.map(request, entityType));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @PutMapping
    public TEntity Update(@RequestBody TEntity request) {
        return this._service.Update(request);
    }

    @Override
    @DeleteMapping({"/{id}"})
    public void Delete(@PathVariable(value = "id") Long id) {
        this._service.Delete(id);
    }

}
