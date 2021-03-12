package com.cropster.challenge.delval.dto;

public class GreenCoffeeDTO {
  private Integer id;
  private String name;

  public GreenCoffeeDTO() {}

  public GreenCoffeeDTO(Integer id, String name) {
    this.id = id;
    this.name = name;
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

  @Override
  public String toString() {
    return "GreenCoffeeDTO [id=" + id + ", name=" + name + "]";
  }
}
