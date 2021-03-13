package com.cropster.challenge.delval.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import com.cropster.challenge.delval.constants.Constants;
import com.cropster.challenge.delval.dto.FacilityDTO;
import com.cropster.challenge.delval.mappers.FacilityMapper;
import com.cropster.challenge.delval.model.Facility;
import com.cropster.challenge.delval.model.GreenCoffee;
import com.cropster.challenge.delval.model.Machine;
import com.cropster.challenge.delval.model.Stock;
import com.cropster.challenge.delval.repositories.FacilityRepository;
import com.cropster.challenge.delval.repositories.GreencoffeeRepository;
import com.cropster.challenge.delval.repositories.MachineRepository;
import com.cropster.challenge.delval.repositories.RoastingProcessRepository;
import com.cropster.challenge.delval.repositories.StockRepository;

public class Roaster {
  private GreencoffeeRepository greencoffeeRepository;

  private MachineRepository machineRepository;

  private StockRepository stockRepository;

  private FacilityRepository facilityRepository;

  private RoastingProcessRepository roastingProcessRepository;

  public Roaster() {}
  
  public Roaster(GreencoffeeRepository greencoffeeRepository, MachineRepository machineRepository,
      StockRepository stockRepository, FacilityRepository facilityRepository,
      RoastingProcessRepository roastingProcessRepository) {
    this.greencoffeeRepository = greencoffeeRepository;
    this.machineRepository = machineRepository;
    this.stockRepository = stockRepository;
    this.facilityRepository = facilityRepository;
    this.roastingProcessRepository = roastingProcessRepository;
  }

  /**
   * Start a random roasting process
   * 
   * @return result 0 if correct
   */
  public int roast() {
    // Pick one facility randomly
    FacilityDTO facility = getRandomFacility();
    
    if (facility == null) {
      return -1;
    }

    // Pick one random machine of the previously chosen facility
//    Machine machine = getRandomMachine(facility);

    // Pick a random green coffee from the facility's stock
//    GreenCoffee greenCoffee = getRandomGreenCoffee(facility);

    // Pick a random start weight
//    BigDecimal weight = getRandomStartWeight(machine);

    // Update stock amount in database
//    stockRepository.updateAmount(facility.getId(), greenCoffee.getId(), weight);

    return 0;
  }

  /**
   * Get a random facility of the database
   * 
   * @return Facility
   */
  private FacilityDTO getRandomFacility() {
    List<Facility> listFacilities = new ArrayList<Facility>();
    List<FacilityDTO> listFacilitiesDTO = new ArrayList<FacilityDTO>();
    Iterable<Facility> iterable = facilityRepository.findAll();
    iterable.forEach(listFacilities::add);
    listFacilitiesDTO = listFacilities.stream()
    .map(this::convertToDto)
    .collect(Collectors.toList());
    Random randomNumber = new Random();
    FacilityDTO facility = listFacilitiesDTO.get(randomNumber.nextInt(listFacilitiesDTO.size()));
    return facility != null ? facility : null;
  }
  
  private FacilityDTO convertToDto(Facility facility) {
    return FacilityMapper.INSTANCE.facilityToDto(facility);
}

  /**
   * Get a random machine that belongs to a facility
   * 
   * @param facility the facility that we want to use
   * @return Machine
   */
  private Machine getRandomMachine(Facility facility) {
    Set<Machine> machines = machineRepository.findByFacility(facility);
    List<Machine> listMachines = new ArrayList<Machine>(machines);
    Random randomNumber = new Random();
    return listMachines.get(randomNumber.nextInt(listMachines.size()));
  }

  /**
   * Get a random green coffee from the facility
   * 
   * @param facility facility from which to take the green coffee
   * @return GreenCoffee
   */
  private GreenCoffee getRandomGreenCoffee(Facility facility) {
    Set<Stock> stock = stockRepository.findByFacility(facility);
    List<Stock> listStock = new ArrayList<Stock>(stock);
    Random randomNumber = new Random();
    return listStock.get(randomNumber.nextInt(listStock.size())).getGreenCoffee();
  }

  /**
   * Get a random starting weight from a machine
   * 
   * @param machine machine used in the process
   * @return the weight
   */
  private BigDecimal getRandomStartWeight(Machine machine) {
    Random randomNumber = new Random();
    double multiplier =
        randomNumber.nextInt((Constants.START_WEIGHT_MAX - Constants.START_WEIGHT_MIN) + 1)
            + Constants.START_WEIGHT_MIN / 100.0;
    return BigDecimal.valueOf(machine.getCapacity() * multiplier);
  }

}
