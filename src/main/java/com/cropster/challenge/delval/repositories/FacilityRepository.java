package com.cropster.challenge.delval.repositories;

import org.springframework.data.repository.CrudRepository;
import com.cropster.challenge.delval.model.Facility;

public interface FacilityRepository extends CrudRepository<Facility, Integer> {
  Facility getByName(String name);

}
