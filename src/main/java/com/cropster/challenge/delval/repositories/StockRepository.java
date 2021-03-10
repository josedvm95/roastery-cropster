package com.cropster.challenge.delval.repositories;

import java.util.Set;
import org.springframework.data.repository.CrudRepository;
import com.cropster.challenge.delval.model.Facility;
import com.cropster.challenge.delval.model.GreenCoffee;
import com.cropster.challenge.delval.model.Stock;
import com.cropster.challenge.delval.model.StockPK;

public interface StockRepository extends CrudRepository<Stock, StockPK> {
  Set<Stock> findByFacility(Facility f);
  Set<Stock> findByGreenCoffee(GreenCoffee f);
  
}
