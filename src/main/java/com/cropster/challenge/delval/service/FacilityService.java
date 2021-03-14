package com.cropster.challenge.delval.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cropster.challenge.delval.model.Facility;
import com.cropster.challenge.delval.repository.FacilityRepository;
import com.cropster.challenge.delval.repository.StockRepository;

@Service
public class FacilityService {
  @Autowired
  private FacilityRepository facilityRepository;
  
  @Autowired
  private StockRepository stockRepository;
  
  /**
   * Delete a single facility
   * 
   * @param f facility to delete
   */
  @Transactional
  public void deleteFacility(Facility f) {
    stockRepository.deleteAll(stockRepository.findByFacility(f));
    facilityRepository.delete(f);
  }
  
  /**
   * Delete all facilities
   * 
   * @param list the list of facilities to delete
   */
  @Transactional
  public void deleteAllFacilities(List<Facility> list) {
    for (Facility f : list) {
      deleteFacility(f);
    }
  }

}
