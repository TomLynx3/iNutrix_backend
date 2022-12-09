package com.rtu.iNutrix.repositories;

import com.rtu.iNutrix.models.entities.ProductCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProductCustomRepository extends JpaRepository<ProductCustom, UUID> {
    List<ProductCustom> findByUser_id(String userID);

    @Query("DELETE FROM ProductCustom pc WHERE pc.id in :ids")
    void deleteById(@Param("ids")List<UUID> ids );
}