package com.cropster.challenge.delval.repository;

import java.util.Set;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.cropster.challenge.delval.model.Facility;
import com.cropster.challenge.delval.model.Machine;

@Repository
public interface MachineRepository extends CrudRepository<Machine, Integer> {
  Set<Machine> findByFacility(Facility f);

}
