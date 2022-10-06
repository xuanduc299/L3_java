package com.globits.da.repository;

import com.globits.da.domain.Village;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VillageRepository extends JpaRepository<Village, UUID> {
    Village getVillageById(UUID id);
}
