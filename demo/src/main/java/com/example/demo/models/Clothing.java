package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.demo.forms.ClothingForm;
import com.example.demo.models.enums.Color;
import com.example.demo.models.enums.Type;

@Entity
public class Clothing extends DomainObject {

    public Clothing () {

    }

    public Clothing ( final ClothingForm form ) {
        setPrimaryColor( Color.valueOf( form.getPrimaryColor() ) );
        setSecondaryColor( Color.valueOf( form.getSecondaryColor() ) );
        setType( Type.valueOf( form.getType() ) );
        if ( form.getPhoto() != null ) {
            setPhoto( form.getPhoto().getOriginalFilename() );
        }
        setId( form.getId() );
    }

    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO )
    Long           id;

    @Enumerated ( EnumType.STRING )
    private Color  primaryColor;

    @Enumerated ( EnumType.STRING )
    private Color  secondaryColor;

    @Enumerated ( EnumType.STRING )
    private Type   type;

    private String photo;

    public Color getPrimaryColor () {
        return primaryColor;
    }

    public void setPrimaryColor ( final Color primaryColor ) {
        this.primaryColor = primaryColor;
    }

    public Color getSecondaryColor () {
        return secondaryColor;
    }

    public void setSecondaryColor ( final Color secondaryColor ) {
        this.secondaryColor = secondaryColor;
    }

    public Type getType () {
        return type;
    }

    public void setType ( final Type type ) {
        this.type = type;
    }

    public String getPhoto () {
        return photo;
    }

    public void setPhoto ( final String photo ) {
        this.photo = photo;
    }

    public void setId ( final Long id ) {
        this.id = id;
    }

    @Override
    public Long getId () {
        return id;
    }

    @Override
    public String toString () {
        return "Clothing [id=" + id + ", primaryColor=" + primaryColor + ", secondaryColor=" + secondaryColor
                + ", type=" + type + ", photo=" + photo + "]";
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
        final Clothing other = (Clothing) obj;
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
        if ( primaryColor != other.primaryColor ) {
            return false;
        }
        if ( secondaryColor != other.secondaryColor ) {
            return false;
        }
        if ( type != other.type ) {
            return false;
        }
        return true;
    }

}
