package com.cropster.challenge.delval.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "roastingProcess")
public class RoastingProcess {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  
  @Column(name = "start_weight", precision = 10, scale = 2, nullable = false)
  private BigDecimal startWeight;
  
  @Column(name = "end_weight", precision = 10, scale = 2, nullable = false)
  private BigDecimal endWeight;
  
  @Column(name = "start_time", nullable = false)
  private Timestamp startTime;
  
  @Column(name = "end_time", nullable = true)
  private Timestamp endTime;
  
  @Column(name = "product_name", length = 128)
  private String productName;
  
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "green_coffee_id", nullable = false)
  private GreenCoffee greenCoffee;
  
  public RoastingProcess() {
  }
  
  public RoastingProcess(BigDecimal startWeight, BigDecimal endWeight, Timestamp startTime,
      Timestamp endTime, String productName, GreenCoffee greenCoffee) {
    this.startWeight = startWeight;
    this.endWeight = endWeight;
    this.startTime = startTime;
    this.endTime = endTime;
    this.productName = productName;
    this.greenCoffee = greenCoffee;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public BigDecimal getStartWeight() {
    return startWeight;
  }

  public void setStartWeight(BigDecimal startWeight) {
    this.startWeight = startWeight;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public BigDecimal getEndWeight() {
    return endWeight;
  }

  public void setEndWeight(BigDecimal endWeight) {
    this.endWeight = endWeight;
  }

  public Timestamp getStartTime() {
    return startTime;
  }

  public void setStartTime(Timestamp startTime) {
    this.startTime = startTime;
  }

  public Timestamp getEndTime() {
    return endTime;
  }

  public void setEndTime(Timestamp endTime) {
    this.endTime = endTime;
  }

  public GreenCoffee getGreenCoffee() {
    return greenCoffee;
  }

  public void setGreenCoffee(GreenCoffee greenCoffee) {
    this.greenCoffee = greenCoffee;
  }
}
