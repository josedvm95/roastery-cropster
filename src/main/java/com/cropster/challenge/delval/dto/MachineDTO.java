package com.cropster.challenge.delval.dto;

public class MachineDTO {
  private Integer id;
  private String name;
  private Integer capacity;
  private Integer facilityId;

  public MachineDTO() {}

  public MachineDTO(Integer id, String name, Integer capacity, Integer facilityId) {
    this.id = id;
    this.name = name;
    this.capacity = capacity;
    this.facilityId = facilityId;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getCapacity() {
    return capacity;
  }

  public void setCapacity(Integer capacity) {
    this.capacity = capacity;
  }

  public Integer getFacilityId() {
    return facilityId;
  }

  public void setFacilityId(Integer facilityId) {
    this.facilityId = facilityId;
  }

  @Override
  public String toString() {
    return "MachineDTO [id=" + id + ", name=" + name + ", capacity=" + capacity + ", facilityId="
        + facilityId + "]";
  }
}
