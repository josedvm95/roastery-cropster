package com.cropster.challenge.delval.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.cropster.challenge.delval.model.Facility;

@Repository
public interface FacilityRepository extends CrudRepository<Facility, Integer> {
  Facility getByName(String name);
  
  @Query(value = "SELECT id, name FROM facilities", nativeQuery = true)
  List<Facility> getAllFacilities();
}
