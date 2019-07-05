package com.totvs.template.Domain.Entities.Base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@MappedSuperclass
public class EntityBase implements IEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private long id;

}
