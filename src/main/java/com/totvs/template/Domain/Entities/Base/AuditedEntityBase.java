package com.totvs.template.Domain.Entities.Base;

import jdk.jfr.Timestamp;
import lombok.Getter;
import lombok.Setter;

public class AuditedEntityBase extends EntityBase {

    @Getter
    private Timestamp CreationTime;

    @Getter @Setter
    private Timestamp UpdatedTime;
}
