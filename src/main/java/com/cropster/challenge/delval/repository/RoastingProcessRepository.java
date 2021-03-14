package com.cropster.challenge.delval.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.cropster.challenge.delval.model.RoastingProcess;

@Repository
public interface RoastingProcessRepository extends CrudRepository<RoastingProcess, Integer> {
  RoastingProcess getByProductName(String productName);
  
  @Query(value = "SELECT * FROM roastingProcess", nativeQuery = true)
  List<RoastingProcess> getAllProcesses();

}
