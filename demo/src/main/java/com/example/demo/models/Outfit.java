package com.example.demo.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.example.demo.models.enums.Occasion;

@Entity
public class Outfit extends DomainObject {
    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO )
    Long             id;

    @Enumerated ( EnumType.STRING )
    private Occasion occasion;

    @ManyToMany ( fetch = FetchType.EAGER )
    List<Clothing>   clothingList;

    public Outfit () {

    }

    public Outfit ( final Occasion occasion, final List<Clothing> clothingList ) {
        this.occasion = occasion;
        this.clothingList = clothingList;
    }

    @Override
    public Long getId () {
        return id;
    }

    public void setId ( final Long id ) {
        this.id = id;
    }

    public Occasion getOccasion () {
        return occasion;
    }

    public void setOccasion ( final Occasion occasion ) {
        this.occasion = occasion;
    }

    public List<Clothing> getClothingList () {
        return clothingList;
    }

    public void setClothingList ( final List<Clothing> clothingList ) {
        this.clothingList = clothingList;
    }

    @Override
    public String toString () {
        return "Outfit [id=" + id + ", occasion=" + occasion + ", clothingList=" + clothingList + "]";
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( clothingList == null ) ? 0 : clothingList.hashCode() );
        result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
        result = prime * result + ( ( occasion == null ) ? 0 : occasion.hashCode() );
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
        final Outfit other = (Outfit) obj;
        if ( clothingList == null ) {
            if ( other.clothingList != null ) {
                return false;
            }
        }
        else if ( !clothingList.equals( other.clothingList ) ) {
            return false;
        }
        if ( id == null ) {
            if ( other.id != null ) {
                return false;
            }
        }
        else if ( !id.equals( other.id ) ) {
            return false;
        }
        if ( occasion != other.occasion ) {
            return false;
        }
        return true;
    }

}
