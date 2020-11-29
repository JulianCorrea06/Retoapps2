package com.example.reto2apps;

import java.io.Serializable;

public class Type implements Serializable {

    private TypeName type;

    public Type() {
    }

    public Type(TypeName type) {
        this.type = type;
    }

    public TypeName getType() {
        return type;
    }

    public void setType(TypeName type) {
        this.type = type;
    }
}
