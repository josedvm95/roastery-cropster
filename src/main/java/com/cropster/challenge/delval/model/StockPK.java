package com.cropster.challenge.delval.model;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class StockPK implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer facilityId;

  private Integer greenCoffeeId;

  public StockPK() {}

  public StockPK(Integer facilityId, Integer greenCoffeeId) {
    super();
    this.facilityId = facilityId;
    this.greenCoffeeId = greenCoffeeId;
  }

  public Integer getFacilityId() {
    return facilityId;
  }

  public void setFacilityId(Integer facilityId) {
    this.facilityId = facilityId;
  }

  public Integer getGreenCoffeeId() {
    return greenCoffeeId;
  }

  public void setGreenCoffeeId(Integer greenCoffeeId) {
    this.greenCoffeeId = greenCoffeeId;
  }
}
