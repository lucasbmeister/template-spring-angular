package com.totvs.template.Util;

import com.google.common.reflect.TypeToken;

public abstract class UtilGetType<T> {
    public TypeToken<T> getType() {
        return new TypeToken<T>(getClass()) {};
    }
}

