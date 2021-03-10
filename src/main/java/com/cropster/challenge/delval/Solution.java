package com.cropster.challenge.delval;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.cropster.challenge.delval.model.Facility;
import com.cropster.challenge.delval.model.GreenCoffee;
import com.cropster.challenge.delval.model.Machine;
import com.cropster.challenge.delval.model.RoastingProcess;
import com.cropster.challenge.delval.model.Stock;
import com.cropster.challenge.delval.repositories.FacilityRepository;
import com.cropster.challenge.delval.repositories.GreencoffeeRepository;
import com.cropster.challenge.delval.repositories.MachineRepository;
import com.cropster.challenge.delval.repositories.StockRepository;

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

  public static void main(String[] args) {
    SpringApplication.run(Solution.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    setInitialValues();

    // Test deleting a single Facility
    // testDeleteFacility();

    // Test deleting all facilities
    // testDeleteAllFacilities();

    // Cleanup the tables
    // cleanUpTables();
  }

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

    Stock s1 = new Stock(f1, gc1, 2000);
    Stock s2 = new Stock(f1, gc2, 3400);
    Stock s3 = new Stock(f2, gc1, 7000);
    List<Stock> listStocks = Arrays.asList(s1, s2, s3);
    stockRepository.saveAll(listStocks);
  }

  /**
   * Delete a single facility
   * 
   * @param f facility to delete
   */
  private void deleteFacility(Facility f) {
    stockRepository.deleteAll(stockRepository.findByFacility(f));
    facilityRepository.delete(f);
  }

  /**
   * Delete all facilities
   * 
   * @param list the list of facilities to delete
   */
  private void deleteAllFacilities(List<Facility> list) {
    for (Facility f : list) {
      deleteFacility(f);
    }
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
    deleteFacility(f1);
    // deleteAllFacilities(listFacility);
  }
  
  /**
   * Test deleting all facilities to see that the the corresponding Machine and Stock also get deleted
   */
  private void testDeleteAllFacilities() {
    List<Facility> listFacility = facilityRepository.getAllFacilities();
    deleteAllFacilities(listFacility);
  }

}
