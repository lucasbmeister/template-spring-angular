package com.totvs.template.Domain.Repository.Base;

import com.totvs.tjf.api.jpa.repository.ApiJpaRepository;
import com.totvs.template.Domain.Entities.Base.IEntityBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface IBaseRepository<TEntity extends IEntityBase> extends JpaRepository<TEntity, Long>, ApiJpaRepository<TEntity> {
}
