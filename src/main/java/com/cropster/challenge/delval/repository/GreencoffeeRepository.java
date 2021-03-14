package com.cropster.challenge.delval.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.cropster.challenge.delval.model.GreenCoffee;

@Repository
public interface GreencoffeeRepository extends CrudRepository<GreenCoffee, Integer> {
  GreenCoffee getByName(String name);

}
