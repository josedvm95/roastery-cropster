package com.cropster.challenge.delval.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cropster.challenge.delval.constants.Constants;
import com.cropster.challenge.delval.dto.FacilityDTO;
import com.cropster.challenge.delval.dto.MachineDTO;
import com.cropster.challenge.delval.dto.StockDTO;
import com.cropster.challenge.delval.mappers.FacilityMapper;
import com.cropster.challenge.delval.mappers.MachineMapper;
import com.cropster.challenge.delval.mappers.StockMapper;
import com.cropster.challenge.delval.model.Facility;
import com.cropster.challenge.delval.model.GreenCoffee;
import com.cropster.challenge.delval.model.Machine;
import com.cropster.challenge.delval.model.RoastingProcess;
import com.cropster.challenge.delval.model.Stock;
import com.cropster.challenge.delval.repository.FacilityRepository;
import com.cropster.challenge.delval.repository.GreencoffeeRepository;
import com.cropster.challenge.delval.repository.MachineRepository;
import com.cropster.challenge.delval.repository.RoastingProcessRepository;
import com.cropster.challenge.delval.repository.StockRepository;

@Service
public class RoasterService {
  @Autowired
  private GreencoffeeRepository greencoffeeRepository;

  @Autowired
  private MachineRepository machineRepository;

  @Autowired
  private StockRepository stockRepository;

  @Autowired
  private FacilityRepository facilityRepository;

  @Autowired
  private StockService stockService;

  /**
   * Start a random roasting process
   * 
   * @return result 0 if correct
   */
  public int roast() {
    // Pick one facility randomly
    FacilityDTO facilityDto = getRandomFacility();

    if (facilityDto == null) {
      return -1;
    }

    // Pick one random machine of the previously chosen facility
    MachineDTO machineDto = getRandomMachine(facilityDto);

    if (machineDto == null) {
      return -2;
    }

    // Pick a random green coffee from the facility's stock
    Integer greenCoffeeId = getRandomGreenCoffeeId(facilityDto);

    if (greenCoffeeId == null) {
      return -3;
    }

    // Pick a random start weight
    BigDecimal weight = getRandomStartWeight(machineDto);

    // Update stock amount in database
    int updated = stockService.updateStock(facilityDto.getId(), greenCoffeeId, weight);

    if (updated != 1) {
      return -4;
    }

    return 0;
  }

  /**
   * Get a random facility of the database
   * 
   * @return FacilityDTO
   */
  private FacilityDTO getRandomFacility() {
    List<Facility> listFacilities = new ArrayList<Facility>();
    List<FacilityDTO> listFacilitiesDto = new ArrayList<FacilityDTO>();
    Iterable<Facility> iterable = facilityRepository.findAll();
    iterable.forEach(listFacilities::add);
    listFacilitiesDto =
        listFacilities.stream().map(this::convertToDto).collect(Collectors.toList());
    Random randomNumber = new Random();
    return listFacilitiesDto.get(randomNumber.nextInt(listFacilitiesDto.size()));
  }

  private FacilityDTO convertToDto(Facility facility) {
    return FacilityMapper.INSTANCE.facilityToDto(facility);
  }

  /**
   * Get a random machine that belongs to a facility
   * 
   * @param facilityDto the facility that we want to use
   * @return MachineDTO
   */
  private MachineDTO getRandomMachine(FacilityDTO facilityDto) {
    Facility facility = FacilityMapper.INSTANCE.dtoToFacility(facilityDto);
    Set<Machine> machines = machineRepository.findByFacility(facility);
    List<Machine> listMachines = new ArrayList<Machine>(machines);
    List<MachineDTO> listMachinesDto = new ArrayList<MachineDTO>();
    listMachines
        .forEach((machine) -> listMachinesDto.add(MachineMapper.INSTANCE.machineToDto(machine)));
    Random randomNumber = new Random();
    return listMachinesDto.get(randomNumber.nextInt(listMachinesDto.size()));
  }

  /**
   * Get a random green coffee from the facility
   * 
   * @param facility facility from which to take the green coffee
   * @return coffee id
   */
  private Integer getRandomGreenCoffeeId(FacilityDTO facilityDto) {
    Facility facility = FacilityMapper.INSTANCE.dtoToFacility(facilityDto);
    Set<Stock> stocks = stockRepository.findByFacility(facility);
    List<Stock> listStock = new ArrayList<Stock>(stocks);
    List<StockDTO> listStockDto = new ArrayList<StockDTO>();
    listStock.forEach((stock) -> listStockDto.add(StockMapper.INSTANCE.stockToDto(stock)));
    Random randomNumber = new Random();
    return listStockDto.get(randomNumber.nextInt(listStockDto.size())).getId().getGreenCoffeeId();
  }

  /**
   * Get a random starting weight from a machine
   * 
   * @param machineDto machine used in the process
   * @return weight
   */
  private BigDecimal getRandomStartWeight(MachineDTO machineDto) {
    Machine machine = MachineMapper.INSTANCE.dtoToMachine(machineDto);
    Random randomNumber = new Random();
    double multiplier =
        (randomNumber.nextInt((Constants.START_WEIGHT_MAX - Constants.START_WEIGHT_MIN) + 1)
            + Constants.START_WEIGHT_MIN) / 100.0;
    return BigDecimal.valueOf(machine.getCapacity() * multiplier);
  }
}
