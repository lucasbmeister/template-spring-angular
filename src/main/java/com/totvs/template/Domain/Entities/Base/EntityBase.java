package com.totvs.template.Domain.Entities.Base;

import lombok.Getter;

import javax.persistence.*;

@MappedSuperclass
public class EntityBase implements IEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long Id;

}
