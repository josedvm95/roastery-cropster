package com.cropster.challenge.delval.service;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cropster.challenge.delval.repository.StockRepository;

@Service
public class StockService {
  @Autowired
  private StockRepository stockRepository;
  
  @Transactional
  public int updateStock(Integer facilityId, Integer greenCoffeeId, BigDecimal amount) {
    return stockRepository.updateAmount(facilityId, greenCoffeeId, amount);
  }
}
