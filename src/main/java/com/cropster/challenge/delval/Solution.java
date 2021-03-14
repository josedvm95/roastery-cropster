package com.cropster.challenge.delval;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.cropster.challenge.delval.constants.Constants;
import com.cropster.challenge.delval.model.Facility;
import com.cropster.challenge.delval.model.GreenCoffee;
import com.cropster.challenge.delval.model.Machine;
import com.cropster.challenge.delval.model.RoastingProcess;
import com.cropster.challenge.delval.model.Stock;
import com.cropster.challenge.delval.repository.FacilityRepository;
import com.cropster.challenge.delval.repository.GreencoffeeRepository;
import com.cropster.challenge.delval.repository.MachineRepository;
import com.cropster.challenge.delval.repository.StockRepository;
import com.cropster.challenge.delval.service.FacilityService;

@SpringBootApplication
public class Solution implements CommandLineRunner {

  @Autowired
  private GreencoffeeRepository greencoffeeRepository;

  @Autowired
  private MachineRepository machineRepository;

  @Autowired
  private StockRepository stockRepository;

  @Autowired
  private FacilityRepository facilityRepository;

  @Autowired
  private FacilityService facilityService;

  public static void main(String[] args) {
    SpringApplication.run(Solution.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    if (isDatabaseEmpty()) {
      generateTask2Data();
    }

    // Values I used for testing Task 1
    // setInitialValues();

    // Test deleting a single Facility
    // testDeleteFacility();

    // Test deleting all facilities
    // testDeleteAllFacilities();
  }

  private boolean isDatabaseEmpty() {
    return facilityRepository.count() == 0 && machineRepository.count() == 0
        && greencoffeeRepository.count() == 0 && stockRepository.count() == 0;
  }

  /**
   * Populate the database with data from the Task 2 of the Challenge
   */
  private void generateTask2Data() {
    List<GreenCoffee> listCoffees = new ArrayList<GreenCoffee>();
    for (String coffee : Constants.GREEN_COFFEES) {
      listCoffees.add(new GreenCoffee(coffee));
    }

    greencoffeeRepository.saveAll(listCoffees);

    List<Facility> listFacilities = new ArrayList<Facility>();
    for (String facility : Constants.FACILITIES) {
      listFacilities.add(new Facility(facility));
    }

    Machine m1 = new Machine(Constants.MACHINES[0], 20, listFacilities.get(0));
    Machine m2 = new Machine(Constants.MACHINES[1], 45, listFacilities.get(0));
    Machine m3 = new Machine(Constants.MACHINES[2], 45, listFacilities.get(0));
    Machine m4 = new Machine(Constants.MACHINES[0], 20, listFacilities.get(1));
    Machine m5 = new Machine(Constants.MACHINES[1], 20, listFacilities.get(1));
    Machine m6 = new Machine(Constants.MACHINES[2], 45, listFacilities.get(1));


    listFacilities.get(0).setMachines(new HashSet<>(Arrays.asList(m1, m2, m3)));
    listFacilities.get(1).setMachines(new HashSet<>(Arrays.asList(m4, m5, m6)));

    facilityRepository.saveAll(listFacilities);

    Stock s1 = new Stock(listFacilities.get(0), listCoffees.get(0), generateRandomWeight());
    Stock s2 = new Stock(listFacilities.get(0), listCoffees.get(1), generateRandomWeight());
    Stock s3 = new Stock(listFacilities.get(0), listCoffees.get(2), generateRandomWeight());
    Stock s4 = new Stock(listFacilities.get(0), listCoffees.get(3), generateRandomWeight());
    Stock s5 = new Stock(listFacilities.get(0), listCoffees.get(4), generateRandomWeight());
    Stock s6 = new Stock(listFacilities.get(1), listCoffees.get(0), generateRandomWeight());
    Stock s7 = new Stock(listFacilities.get(1), listCoffees.get(1), generateRandomWeight());
    Stock s8 = new Stock(listFacilities.get(1), listCoffees.get(2), generateRandomWeight());
    Stock s9 = new Stock(listFacilities.get(1), listCoffees.get(3), generateRandomWeight());
    Stock s10 = new Stock(listFacilities.get(1), listCoffees.get(4), generateRandomWeight());
    List<Stock> listStocks = Arrays.asList(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10);
    stockRepository.saveAll(listStocks);
  }

  /**
   * Generate a random weight between MAX_WEIGHT and MIN_WEIGHT
   * 
   * @return Integer random weight
   */
  private BigDecimal generateRandomWeight() {
    Random randomNumber = new Random();
    return BigDecimal
        .valueOf(randomNumber.nextInt((Constants.MAX_WEIGHT - Constants.MIN_WEIGHT) + 1)
            + Constants.MIN_WEIGHT);
  }

  /**
   * Populate the database with sample data
   */
  private void setInitialValues() {
    // Insert a few test values in the database
    GreenCoffee gc1 = new GreenCoffee("Java");
    GreenCoffee gc2 = new GreenCoffee("Sumatra");
    List<GreenCoffee> listCoffees = Arrays.asList(gc1, gc2);

    Timestamp startTime = Timestamp.valueOf(LocalDateTime.now());
    RoastingProcess rp1 = new RoastingProcess(BigDecimal.valueOf(100), BigDecimal.valueOf(50),
        startTime, null, "roasted1", gc1);

    gc1.setRoastingProcesses(new HashSet<>(Arrays.asList(rp1)));

    greencoffeeRepository.saveAll(listCoffees);

    Facility f1 = new Facility("facility1");
    Facility f2 = new Facility("facility2");
    List<Facility> listFacilities = Arrays.asList(f1, f2);

    Machine m1 = new Machine("machine1", 1000, f1);
    Machine m2 = new Machine("machine2", 1200, f1);
    Machine m4 = new Machine("machine2", 800, f2);
    Machine m3 = new Machine("machine3", 3000, f2);

    f1.setMachines(new HashSet<>(Arrays.asList(m1, m2)));
    f2.setMachines(new HashSet<>(Arrays.asList(m3, m4)));

    facilityRepository.saveAll(listFacilities);

    Stock s1 = new Stock(f1, gc1, BigDecimal.valueOf(2000));
    Stock s2 = new Stock(f1, gc2, BigDecimal.valueOf(3400));
    Stock s3 = new Stock(f2, gc1, BigDecimal.valueOf(7000));
    List<Stock> listStocks = Arrays.asList(s1, s2, s3);
    stockRepository.saveAll(listStocks);
  }

  /**
   * Clean up the tables
   */
  private void cleanUpTables() {
    stockRepository.deleteAll();
    machineRepository.deleteAll();
    greencoffeeRepository.deleteAll();
    facilityRepository.deleteAll();
  }

  /**
   * Test deleting one facility to see that the the corresponding Machine and Stock also get deleted
   */
  private void testDeleteFacility() {
    Facility f1 = facilityRepository.getByName("facility1");
    facilityService.deleteFacility(f1);
  }

  /**
   * Test deleting all facilities to see that the the corresponding Machine and Stock also get
   * deleted
   */
  private void testDeleteAllFacilities() {
    List<Facility> listFacility = facilityRepository.getAllFacilities();
    facilityService.deleteAllFacilities(listFacility);
  }

}
