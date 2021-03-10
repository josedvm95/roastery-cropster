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

@Controller
@RequestMapping(path="/demo")
public class MainController {
  @Autowired
  private GreencoffeeRepository greencoffeeRepository;
  
  @PostMapping(path="/add")
  public @ResponseBody String addNewCoffee (@RequestParam String name) {
    GreenCoffee n = new GreenCoffee();
    n.setName(name);
    greencoffeeRepository.save(n);
    return "Saved";
  }
  
  @GetMapping(path="/all")
  public @ResponseBody Iterable<GreenCoffee> getAllCoffees() {
    return greencoffeeRepository.findAll();
  }

}
