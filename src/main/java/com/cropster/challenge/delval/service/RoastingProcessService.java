package com.cropster.challenge.delval.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.cropster.challenge.delval.model.GreenCoffee;
import com.cropster.challenge.delval.model.RoastingProcess;
import com.cropster.challenge.delval.repository.RoastingProcessRepository;

public class RoastingProcessService {
  @Autowired
  private RoastingProcessRepository roastingProcessRepository;

  @Transactional
  public void createRoastingProcess(BigDecimal startWeight, BigDecimal endWeight,
      Timestamp startTime, Timestamp endTime, String productName, GreenCoffee greenCoffee) {
    RoastingProcess roastingProcess =
        new RoastingProcess(startWeight, endWeight, startTime, endTime, productName, greenCoffee);
    roastingProcessRepository.save(roastingProcess);
  }
}
