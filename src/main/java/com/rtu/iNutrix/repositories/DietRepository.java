package com.rtu.iNutrix.repositories;

import com.rtu.iNutrix.models.entities.Diet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DietRepository extends JpaRepository<Diet, UUID> {
}