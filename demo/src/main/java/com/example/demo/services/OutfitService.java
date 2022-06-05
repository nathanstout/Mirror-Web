package com.example.demo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.demo.models.Outfit;
import com.example.demo.models.enums.Occasion;
import com.example.demo.repositories.OutfitRepository;

@Component
@Transactional
public class OutfitService extends Service {
    @Autowired
    private OutfitRepository repository;

    @Override
    protected JpaRepository getRepository () {
        return repository;
    }

    public List<Outfit> findByOccasion ( final Occasion occasion ) {
        return repository.findByOccasion( occasion );
    }
}
