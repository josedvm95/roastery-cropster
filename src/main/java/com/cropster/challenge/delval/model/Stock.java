package com.cropster.challenge.delval.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "stock")
public class Stock {
  @EmbeddedId
  private StockPK id;

  @MapsId("facilityId")
  @ManyToOne
  private Facility facility;

  @MapsId("greenCoffeeId")
  @ManyToOne
  private GreenCoffee greenCoffee;

  private Integer amount;

  public Stock() {}

  public Stock(Facility facility, GreenCoffee greenCoffee, Integer amount) {
    this.facility = facility;
    this.greenCoffee = greenCoffee;
    this.amount = amount;
    this.id = new StockPK();
  }

  public StockPK getId() {
    return id;
  }

  public void setId(StockPK id) {
    this.id = id;
  }

  public Facility getFacility() {
    return facility;
  }

  public void setFacility(Facility facility) {
    this.facility = facility;
  }

  public GreenCoffee getGreenCoffee() {
    return greenCoffee;
  }

  public void setGreenCoffee(GreenCoffee greenCoffee) {
    this.greenCoffee = greenCoffee;
  }

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }
}
