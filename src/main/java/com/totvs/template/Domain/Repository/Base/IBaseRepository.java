package com.totvs.template.Domain.Repository.Base;

import com.totvs.tjf.api.context.v1.request.ApiFieldRequest;
import com.totvs.tjf.api.context.v1.request.ApiPageRequest;
import com.totvs.tjf.api.context.v1.request.ApiSortRequest;
import com.totvs.tjf.api.jpa.repository.ApiJpaRepository;
import com.totvs.template.Domain.Entities.Base.IEntityBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Collection;
import java.util.List;


@NoRepositoryBean
public interface IBaseRepository<TEntity extends IEntityBase> extends JpaRepository<TEntity, Long>, ApiJpaRepository<TEntity> {
    //Collection<TEntity> findAll(ApiPageRequest page, ApiSortRequest sort, ApiFieldRequest field);
}
