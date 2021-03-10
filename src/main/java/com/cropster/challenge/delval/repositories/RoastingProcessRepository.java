package com.cropster.challenge.delval.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.cropster.challenge.delval.model.RoastingProcess;

public interface RoastingProcessRepository extends CrudRepository<RoastingProcess, Integer> {
  RoastingProcess getByProductName(String productName);
  
  @Query(value = "SELECT * FROM roastingProcess", nativeQuery = true)
  List<RoastingProcess> getAllProcesses();

}
