package com.cropster.challenge.delval.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cropster.challenge.delval.constants.Constants;
import com.cropster.challenge.delval.dto.FacilityDTO;
import com.cropster.challenge.delval.dto.MachineDTO;
import com.cropster.challenge.delval.dto.StockDTO;
import com.cropster.challenge.delval.mappers.FacilityMapper;
import com.cropster.challenge.delval.mappers.MachineMapper;
import com.cropster.challenge.delval.mappers.RoastingProcessMapper;
import com.cropster.challenge.delval.mappers.StockMapper;
import com.cropster.challenge.delval.model.Facility;
import com.cropster.challenge.delval.model.GreenCoffee;
import com.cropster.challenge.delval.model.Machine;
import com.cropster.challenge.delval.model.RoasterResponse;
import com.cropster.challenge.delval.model.RoastingProcess;
import com.cropster.challenge.delval.model.Stock;
import com.cropster.challenge.delval.repository.FacilityRepository;
import com.cropster.challenge.delval.repository.GreencoffeeRepository;
import com.cropster.challenge.delval.repository.MachineRepository;
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

  @Autowired
  private RoastingProcessService roastingProcessService;

  /**
   * Start a random roasting process
   * 
   * @return RoasterResponse with a responseCode of 0 if it is correct
   */
  public RoasterResponse randomRoast() {
    RoasterResponse response = new RoasterResponse();
    RoastingProcess roastingProcess = new RoastingProcess();

    // Pick one facility randomly
    FacilityDTO facilityDto = getRandomFacility();

    if (facilityDto == null) {
      response.setResponseCode(-1);
      return response;
    }

    // Pick one random machine of the previously chosen facility
    MachineDTO machineDto = getRandomMachine(facilityDto);

    if (machineDto == null) {
      response.setResponseCode(-2);
      return response;
    }

    // Pick a random green coffee from the facility's stock
    Integer greenCoffeeId = getRandomGreenCoffeeId(facilityDto);

    if (greenCoffeeId == null) {
      response.setResponseCode(-3);
      return response;
    }

    // Pick a random start weight
    BigDecimal startWeight = getRandomStartWeight(machineDto);

    // Update stock amount in database
    int updated = stockService.updateStock(facilityDto.getId(), greenCoffeeId, startWeight);

    if (updated != 1) {
      response.setResponseCode(-4);
      return response;
    }

    int processTime = getRandomDurationTime();

    int chargingTime = getChargingTime();

    roastingProcess.setStartWeight(startWeight);
    roastingProcess.setEndWeight(getRandomEndWeight(startWeight));
    LocalDateTime startTime = getStartDateTime(processTime + chargingTime);
    roastingProcess.setStartTime(Timestamp.valueOf(startTime));
    roastingProcess
        .setEndTime(Timestamp.valueOf(startTime.plusMinutes(processTime + chargingTime)));

    GreenCoffee greenCoffee = greencoffeeRepository.findById(greenCoffeeId).orElse(null);
    roastingProcess.setGreenCoffee(greenCoffee);

    roastingProcess.setProductName(getRandomProductName());

    roastingProcessService.saveRoastingProcess(roastingProcess);

    response
        .setRoastingProcess(RoastingProcessMapper.INSTANCE.roastingProcessToDto(roastingProcess));
    response.setResponseCode(0);
    return response;
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

  /**
   * Generate a random weight between MAX_WEIGHT and MIN_WEIGHT
   * 
   * @return Integer random weight
   */
  private Integer getRandomDurationTime() {
    Random randomNumber = new Random();
    return randomNumber.nextInt((Constants.MAX_DURATION - Constants.MIN_DURATION) + 1)
        + Constants.MIN_DURATION;
  }

  /**
   * Generate a random weight between MAX_WEIGHT and MIN_WEIGHT
   * 
   * @return Integer random weight
   */
  private Integer getChargingTime() {
    // We consider it takes 7.5 min on average to charge the machine
    int min = getPoisson((Constants.MAX_CHARGE_TIME + Constants.MIN_CHARGE_TIME) / 2);
    if (min < Constants.MIN_CHARGE_TIME) {
      min = Constants.MIN_CHARGE_TIME;
    } else if (min > Constants.MAX_CHARGE_TIME) {
      min = Constants.MAX_CHARGE_TIME;
    }
    return min;
  }

  /**
   * Get a random number with Poisson distribution
   * 
   * @param lambda
   * @return number following Poisson distribution
   */
  private int getPoisson(double lambda) {
    double limit = Math.exp(-lambda);
    double p = 1.0;
    int k = 0;

    do {
      k++;
      p *= Math.random();
    } while (p > limit);

    return k - 1;
  }

  private LocalDateTime getStartDateTime(Integer duration) {
    return LocalDate.now().atTime(getStartTime(duration));
  }

  private LocalTime getStartTime(Integer duration) {
    Random randomNumber = new Random();
    int min = Constants.OPEN_TIME.getHour() * 60 + Constants.OPEN_TIME.getMinute();
    int max = Constants.CLOSE_TIME.minusMinutes(duration).getHour() * 60
        + Constants.CLOSE_TIME.getMinute();
    int minutes = randomNumber.nextInt((max - min) + 1) + min;

    return LocalTime.of(minutes / 60, minutes % 60);
  }

  private String getRandomProductName() {
    Random randomNumber = new Random();
    return Constants.PRODUCTS[randomNumber.nextInt(Constants.PRODUCTS.length)];
  }

  /**
   * Get a random weight loss
   * 
   * @param machineDto machine used in the process
   * @return weight
   */
  private BigDecimal getRandomEndWeight(BigDecimal startWeight) {
    Random randomNumber = new Random();
    double multiplier = 1 - (randomNumber.nextInt((Constants.MAX_WEIGHT_LOSS - Constants.MIN_WEIGHT_LOSS) + 1)
        + Constants.MIN_WEIGHT_LOSS) / 100.0;
    return startWeight.multiply(BigDecimal.valueOf(multiplier));
  }
}
