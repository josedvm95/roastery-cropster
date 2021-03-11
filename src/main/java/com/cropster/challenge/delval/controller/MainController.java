package com.cropster.challenge.delval.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cropster.challenge.delval.model.GreenCoffee;
import com.cropster.challenge.delval.repositories.GreencoffeeRepository;
import com.cropster.challenge.delval.repositories.StockRepository;

@Controller
@RequestMapping(path = "/demo")
public class MainController {
  @Autowired
  private GreencoffeeRepository greencoffeeRepository;

  @Autowired
  private StockRepository stockRepository;

  @PostMapping(path = "/add")
  public @ResponseBody String addNewCoffee(@RequestParam String name) {
    GreenCoffee n = new GreenCoffee();
    n.setName(name);
    greencoffeeRepository.save(n);
    return "Saved";
  }

  @PostMapping(path = "/roast")
  public @ResponseBody String roastCoffee(@RequestParam Integer facilityId,
      @RequestParam String greenCoffee, @RequestParam Integer amount) {
    GreenCoffee coffee = greencoffeeRepository.getByName(greenCoffee);
    stockRepository.updateAmount(facilityId, coffee.getId(), amount);
    return String.valueOf(coffee.getId());
  }

  @GetMapping(path = "/all")
  public @ResponseBody Iterable<GreenCoffee> getAllCoffees() {
    return greencoffeeRepository.findAll();
  }

}
