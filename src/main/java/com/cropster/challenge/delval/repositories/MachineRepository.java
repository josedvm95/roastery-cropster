package com.cropster.challenge.delval.repositories;

import java.util.Set;
import org.springframework.data.repository.CrudRepository;
import com.cropster.challenge.delval.model.Facility;
import com.cropster.challenge.delval.model.Machine;

public interface MachineRepository extends CrudRepository<Machine, Integer> {
  Set<Machine> findByFacility(Facility f);

}
