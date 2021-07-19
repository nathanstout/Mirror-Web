package com.example.demo.models.enums;

import java.util.HashMap;
import java.util.Map;

public enum Occasion {
    Casual ( 1, "Casual" ), Formal ( 2, "Formal" ), Active ( 3, "Active" ), NotSpecified ( 4, "Not Specified" ),;

    private int    code;

    private String name;

    private Occasion ( final int code, final String name ) {
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

    public static Occasion parse ( final String occasionStr ) {
        for ( final Occasion occasion : values() ) {
            if ( occasion.getName().equals( occasionStr ) ) {
                return occasion;
            }
        }
        return NotSpecified;
    }

}
