package com.totvs.template.Domain.Enums;

public enum ParameterTypeEnum {
    TENTITY(0),
    TCREATE_DTO(1),
    TLIST_DTO(2),
    TENTITY_DTO(3);

    public static int TENTITY_VALUE = 0;
    public static int TCREATE_DTO_VALUE = 1;
    public static int TLIST_DTO_VALUE = 2;
    public static int TENTITY_DTO_VALUE = 3;

    private int _value;

    ParameterTypeEnum(int value) {
        this._value = value;
    }

    public int getType() {
        return this._value;
    }
}
