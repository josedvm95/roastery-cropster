package com.cropster.challenge.delval.repositories;

import org.springframework.data.repository.CrudRepository;
import com.cropster.challenge.delval.model.GreenCoffee;

public interface GreencoffeeRepository extends CrudRepository<GreenCoffee, Integer> {
  GreenCoffee getByName(String name);

}
