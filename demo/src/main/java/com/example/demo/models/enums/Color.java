package com.example.demo.models.enums;

import java.util.HashMap;
import java.util.Map;

public enum Color {

    RED ( 1, "Red" ),
    PINK ( 2, "Pink" ),
    ORANGE ( 3, "Orange" ),
    YELLOW ( 4, "Yellow" ),
    BLUE ( 5, "Blue" ),
    PURPLE ( 6, "Purple" ),
    GREEN ( 7, "Green" ),
    WHITE ( 8, "White" ),
    GRAY ( 9, "Gray" ),
    BLACK ( 10, "Black" ),
    NONE ( 11, "None" ),;

    private int    code;

    private String name;

    private Color ( final int code, final String name ) {
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

    public static Color parse ( final String colorStr ) {
        for ( final Color color : values() ) {
            if ( color.getName().equals( colorStr ) ) {
                return color;
            }
        }
        return NONE;
    }

}
