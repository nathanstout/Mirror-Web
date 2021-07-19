package com.example.demo.models.enums;

import java.util.HashMap;
import java.util.Map;

public enum Type {
    Tops ( 1, "Tops" ), Bottoms ( 2, "Bottoms" ), Shoes ( 3, "Shoes" ), NotSpecified ( 4, "Not Specified" ),;

    private int    code;

    private String name;

    private Type ( final int code, final String name ) {
        this.code = code;
        this.name = name;
    }

    public int getCode () {
        return this.code;
    }

    public String getName () {
        return this.name;
    }

    public Map<String, Object> getInfo () {
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put( "id", name() );
        map.put( "name", getName() );
        return map;
    }

    public static Type parse ( final String typeStr ) {
        for ( final Type type : values() ) {
            if ( type.getName().equals( typeStr ) ) {
                return type;
            }
        }
        return NotSpecified;
    }

}
