package com.rtu.iNutrix.repositories;

import com.rtu.iNutrix.models.entities.DietProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface DietProductRepository extends JpaRepository<DietProduct, UUID>, JpaSpecificationExecutor<DietProduct> {
}