package com.example.demo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.demo.models.Clothing;
import com.example.demo.models.enums.Color;
import com.example.demo.models.enums.Type;
import com.example.demo.repositories.ClothingRepository;

@Component
@Transactional
public class ClothingService extends Service {

    @Autowired
    private ClothingRepository repository;

    @Override
    protected JpaRepository getRepository () {
        return repository;
    }

    public List<Clothing> findByType ( final Type type ) {
        return repository.findByType( type );
    }

    public List<Clothing> findByPhoto ( final String photo ) {
        return repository.findByPhoto( photo );
    }

    public List<Clothing> findByPrimaryColor ( final Color color ) {
        return repository.findByPrimaryColor( color );
    }

    public List<Clothing> findBySecondaryColor ( final Color color ) {
        return repository.findBySecondaryColor( color );
    }

    public List<Clothing> findByTypeAndPrimaryColorAndSecondaryColor ( final Type type, final Color primaryColor,
            final Color secondaryColor ) {
        return repository.findByTypeAndPrimaryColorAndSecondaryColor( type, primaryColor, secondaryColor );
    }
}
