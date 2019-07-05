package com.totvs.template.Domain.Dto.Base;

import lombok.Getter;
import lombok.Setter;

public class EntityBaseDto implements IEntityBaseDto {

    @Getter @Setter
    public long id;

    public EntityBaseDto() {
        return;
    }

}
