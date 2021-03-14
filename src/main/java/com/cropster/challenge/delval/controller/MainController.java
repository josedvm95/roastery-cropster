package com.cropster.challenge.delval.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cropster.challenge.delval.dto.FacilityDTO;
import com.cropster.challenge.delval.dto.GreenCoffeeDTO;
import com.cropster.challenge.delval.dto.MachineDTO;
import com.cropster.challenge.delval.mappers.FacilityMapper;
import com.cropster.challenge.delval.mappers.GreenCoffeeMapper;
import com.cropster.challenge.delval.mappers.MachineMapper;
import com.cropster.challenge.delval.model.Facility;
import com.cropster.challenge.delval.model.GreenCoffee;
import com.cropster.challenge.delval.model.Machine;
import com.cropster.challenge.delval.model.RoasterResponse;
import com.cropster.challenge.delval.repository.FacilityRepository;
import com.cropster.challenge.delval.repository.GreencoffeeRepository;
import com.cropster.challenge.delval.repository.MachineRepository;
import com.cropster.challenge.delval.repository.RoastingProcessRepository;
import com.cropster.challenge.delval.repository.StockRepository;
import com.cropster.challenge.delval.service.RoasterService;

@Controller
@RequestMapping(path = "/roastery")
public class MainController {
  @Autowired
  private GreencoffeeRepository greencoffeeRepository;

  @Autowired
  private FacilityRepository facilityRepository;

  @Autowired
  private StockRepository stockRepository;

  @Autowired
  private MachineRepository machineRepository;

  @Autowired
  private RoastingProcessRepository roastingProcessRepository;
  
  @Autowired
  private RoasterService roasterService;

  @PostMapping(path = "/add")
  public @ResponseBody String addNewCoffee(@RequestParam String name) {
    GreenCoffee n = new GreenCoffee();
    n.setName(name);
    greencoffeeRepository.save(n);
    return "Saved";
  }

  @PostMapping(path = "/roast")
  public @ResponseBody String roastCoffee(@RequestParam Integer facilityId,
      @RequestParam String greenCoffee, @RequestParam BigDecimal amount) {
    GreenCoffee coffee = greencoffeeRepository.getByName(greenCoffee);
    stockRepository.updateAmount(facilityId, coffee.getId(), amount);
    return String.valueOf(coffee.getId());
  }

  @GetMapping(path = "/roast-random")
  public @ResponseBody RoasterResponse roastRandomCoffee() {
    return roasterService.randomRoast();
  }

  @GetMapping(path = "/greencoffees")
  public @ResponseBody List<GreenCoffeeDTO> getAllCoffees() {
    List<GreenCoffee> listGreenCoffee = new ArrayList<GreenCoffee>();
    List<GreenCoffeeDTO> listGreenCoffeeDto = new ArrayList<GreenCoffeeDTO>();
    Iterable<GreenCoffee> iterable = greencoffeeRepository.findAll();
    iterable.forEach(listGreenCoffee::add);
    listGreenCoffee.forEach(
        (coffee) -> listGreenCoffeeDto.add(GreenCoffeeMapper.INSTANCE.greenCoffeeToDto(coffee)));
    return listGreenCoffeeDto;
  }

  @GetMapping(path = "/greencoffees/{id}")
  public @ResponseBody GreenCoffeeDTO getGreenCoffee(@PathVariable Integer id) {
    GreenCoffee greenCoffee = greencoffeeRepository.findById(id).orElse(new GreenCoffee());
    return GreenCoffeeMapper.INSTANCE.greenCoffeeToDto(greenCoffee);
  }

  @GetMapping(path = "/facilities")
  public @ResponseBody List<FacilityDTO> getAllFacilities() {
    List<Facility> listFacility = new ArrayList<Facility>();
    List<FacilityDTO> listFacilityDto = new ArrayList<FacilityDTO>();
    Iterable<Facility> iterable = facilityRepository.findAll();
    iterable.forEach(listFacility::add);
    listFacility.forEach(
        (facility) -> listFacilityDto.add(FacilityMapper.INSTANCE.facilityToDto(facility)));
    return listFacilityDto;
  }

  @GetMapping(path = "/facilities/{id}")
  public @ResponseBody FacilityDTO getFacility(@PathVariable Integer id) {
    Facility facility = facilityRepository.findById(id).orElse(new Facility());
    return FacilityMapper.INSTANCE.facilityToDto(facility);
  }

  @GetMapping(path = "/machines")
  public @ResponseBody List<MachineDTO> getAllMachines() {
    List<Machine> listMachine = new ArrayList<Machine>();
    List<MachineDTO> listMachineDto = new ArrayList<MachineDTO>();
    Iterable<Machine> iterable = machineRepository.findAll();
    iterable.forEach(listMachine::add);
    listMachine
        .forEach((machine) -> listMachineDto.add(MachineMapper.INSTANCE.machineToDto(machine)));
    return listMachineDto;
  }

  @GetMapping(path = "/machines/{id}")
  public @ResponseBody MachineDTO getMachine(@PathVariable Integer id) {
    Machine machine = machineRepository.findById(id).orElse(new Machine());
    return MachineMapper.INSTANCE.machineToDto(machine);
  }

}
