package com.cropster.challenge.delval.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.cropster.challenge.delval.model.Facility;

public interface FacilityRepository extends CrudRepository<Facility, Integer> {
  Facility getByName(String name);
  
  @Query(value = "SELECT amount, facility_id, green_coffee_id FROM facilities", nativeQuery = true)
  List<Facility> getAllFacilities();

}
