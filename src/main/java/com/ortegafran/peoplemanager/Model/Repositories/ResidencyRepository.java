package com.ortegafran.peoplemanager.Model.Repositories;

import com.ortegafran.peoplemanager.Model.Entities.Residency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ResidencyRepository extends JpaRepository<Residency, UUID> {

}
