package com.example.demo.forms;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.models.Clothing;
import com.example.demo.models.enums.Color;
import com.example.demo.models.enums.Type;

public class ClothingForm {

    private String        primaryColor;

    private String        secondaryColor;

    private String        type;

    private MultipartFile photo;

    private Long          id;

    public ClothingForm () {

    }

    public ClothingForm ( final String id, final String primaryColor, final String secondaryColor, final String type,
            final MultipartFile photo ) {
        setPrimaryColor( primaryColor );
        setSecondaryColor( secondaryColor );
        setType( type );
        setPhoto( photo );
        setId( Long.parseLong( id ) );
    }

    public ClothingForm ( final Long id, final Color primaryColor, final Color secondaryColor, final Type type,
            final MultipartFile photo ) {
        setPrimaryColor( primaryColor.toString() );
        setSecondaryColor( secondaryColor.toString() );
        setType( type.toString() );
        setPhoto( photo );
        setId( id );
    }

    public ClothingForm ( final Clothing clothing ) {
        setPrimaryColor( clothing.getPrimaryColor().toString() );
        setSecondaryColor( clothing.getSecondaryColor().toString() );
        setType( clothing.getType().toString() );
        setId( clothing.getId() );
    }

    public String getPrimaryColor () {
        return primaryColor;
    }

    public void setPrimaryColor ( final String primaryColor ) {
        this.primaryColor = primaryColor;
    }

    public String getSecondaryColor () {
        return secondaryColor;
    }

    public void setSecondaryColor ( final String secondaryColor ) {
        this.secondaryColor = secondaryColor;
    }

    public void setType ( final String type ) {
        this.type = type;
    }

    public String getType () {
        return type;
    }

    public MultipartFile getPhoto () {
        return photo;
    }

    public void setPhoto ( final MultipartFile photo ) {
        this.photo = photo;
    }

    public Long getId () {
        return id;
    }

    public void setId ( final Long l ) {
        id = l;
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
        result = prime * result + ( ( photo == null ) ? 0 : photo.hashCode() );
        result = prime * result + ( ( primaryColor == null ) ? 0 : primaryColor.hashCode() );
        result = prime * result + ( ( secondaryColor == null ) ? 0 : secondaryColor.hashCode() );
        result = prime * result + ( ( type == null ) ? 0 : type.hashCode() );
        return result;
    }

    @Override
    public boolean equals ( final Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final ClothingForm other = (ClothingForm) obj;
        if ( id == null ) {
            if ( other.id != null ) {
                return false;
            }
        }
        else if ( !id.equals( other.id ) ) {
            return false;
        }
        if ( photo == null ) {
            if ( other.photo != null ) {
                return false;
            }
        }
        else if ( !photo.equals( other.photo ) ) {
            return false;
        }
        if ( primaryColor == null ) {
            if ( other.primaryColor != null ) {
                return false;
            }
        }
        else if ( !primaryColor.equals( other.primaryColor ) ) {
            return false;
        }
        if ( secondaryColor == null ) {
            if ( other.secondaryColor != null ) {
                return false;
            }
        }
        else if ( !secondaryColor.equals( other.secondaryColor ) ) {
            return false;
        }
        if ( type == null ) {
            if ( other.type != null ) {
                return false;
            }
        }
        else if ( !type.equals( other.type ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString () {
        return "ClothingForm [primaryColor=" + primaryColor + ", secondaryColor=" + secondaryColor + ", type=" + type
                + ", photo=" + photo + ", id=" + id + "]";
    }

}
