package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Outfit;
import com.example.demo.models.enums.Occasion;

public interface OutfitRepository extends JpaRepository<Outfit, Long> {
    public List<Outfit> findByOccasion ( Occasion occasion );
}
