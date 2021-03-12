package com.cropster.challenge.delval.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class RoastingProcessDTO {
  private Integer id;
  private BigDecimal startWeight;
  private BigDecimal endWeight;
  private Timestamp startTime;
  private Timestamp endTime;
  private String productName;
  private Integer greenCoffeeId;
  
  public RoastingProcessDTO() {
  }
  
  public RoastingProcessDTO(Integer id, BigDecimal startWeight, BigDecimal endWeight,
      Timestamp startTime, Timestamp endTime, String productName, Integer greenCoffeeId) {
    this.id = id;
    this.startWeight = startWeight;
    this.endWeight = endWeight;
    this.startTime = startTime;
    this.endTime = endTime;
    this.productName = productName;
    this.greenCoffeeId = greenCoffeeId;
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

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public Integer getGreenCoffeeId() {
    return greenCoffeeId;
  }

  public void setGreenCoffeeId(Integer greenCoffeeId) {
    this.greenCoffeeId = greenCoffeeId;
  }

  @Override
  public String toString() {
    return "RoastingProcessDTO [id=" + id + ", startWeight=" + startWeight + ", endWeight="
        + endWeight + ", startTime=" + startTime + ", endTime=" + endTime + ", productName="
        + productName + ", greenCoffeeId=" + greenCoffeeId + "]";
  }
}
