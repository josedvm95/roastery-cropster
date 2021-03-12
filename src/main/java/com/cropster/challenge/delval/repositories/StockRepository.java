package com.cropster.challenge.delval.repositories;

import java.math.BigDecimal;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.cropster.challenge.delval.model.Facility;
import com.cropster.challenge.delval.model.GreenCoffee;
import com.cropster.challenge.delval.model.Stock;
import com.cropster.challenge.delval.model.StockPK;

public interface StockRepository extends CrudRepository<Stock, StockPK> {
  Set<Stock> findByFacility(Facility f);

  Set<Stock> findByGreenCoffee(GreenCoffee f);

  @Query(
      value = "SELECT * FROM stock WHERE facility_id = :facilityId and green_coffee_id = :greenCoffeeId",
      nativeQuery = true)
  Stock getByCoffeeAndFacility(Integer facilityId, Integer greenCoffeeId);

  @Modifying(clearAutomatically = true, flushAutomatically = true)
  @Transactional
  @Query(
      value = "UPDATE stock SET amount = amount - :amount WHERE facility_id = :facilityId and green_coffee_id = :greenCoffeeId and amount > 0",
      nativeQuery = true)
  void updateAmount(Integer facilityId, Integer greenCoffeeId, BigDecimal amount);

}
