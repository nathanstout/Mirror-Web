package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Clothing;
import com.example.demo.models.enums.Color;
import com.example.demo.models.enums.Type;

public interface ClothingRepository extends JpaRepository<Clothing, Long> {

    public List<Clothing> findByType ( Type type );

    public List<Clothing> findByPhoto ( String photo );

    public List<Clothing> findByPrimaryColor ( Color color );

    public List<Clothing> findBySecondaryColor ( Color color );

    public List<Clothing> findByTypeAndPrimaryColorAndSecondaryColor ( Type type, Color primaryColor,
            Color secondaryColor );

    // @Query ( "select c from demo.clothing c where c.type = :type and
    // c.primary_color = :primary_color and c.secondary_color =
    // :secondary_color" )
    // List<Clothing> findByTypeAndPrimaryColorAndSecondaryColor ( @Param (
    // "secondary_color" ) Color secondary_color,
    // @Param ( "primary_color" ) Color primary_color, @Param ( "type" ) Type
    // type );

}
