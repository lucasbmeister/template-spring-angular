package com.totvs.template.Domain.Dto.Base;

import lombok.Getter;

public class EntityBaseDto implements IEntityBaseDto {

    @Getter
    public long Id;

    public EntityBaseDto() {
        return;
    }

}
