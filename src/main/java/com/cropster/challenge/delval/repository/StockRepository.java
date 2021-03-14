package com.cropster.challenge.delval.repository;

import java.math.BigDecimal;
import java.util.Set;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.cropster.challenge.delval.model.Facility;
import com.cropster.challenge.delval.model.GreenCoffee;
import com.cropster.challenge.delval.model.Stock;
import com.cropster.challenge.delval.model.StockPK;

@Repository
public interface StockRepository extends CrudRepository<Stock, StockPK> {
  Set<Stock> findByFacility(Facility f);

  Set<Stock> findByGreenCoffee(GreenCoffee f);

  @Query(
      value = "SELECT * FROM stock WHERE facility_id = :facilityId and green_coffee_id = :greenCoffeeId",
      nativeQuery = true)
  Stock getByCoffeeAndFacility(Integer facilityId, Integer greenCoffeeId);

  @Modifying(clearAutomatically = true, flushAutomatically = true)
  @Query(
      value = "UPDATE stock SET amount = amount - :amount WHERE facility_id = :facilityId and green_coffee_id = :greenCoffeeId and amount > 0",
      nativeQuery = true)
  int updateAmount(Integer facilityId, Integer greenCoffeeId, BigDecimal amount);

}
