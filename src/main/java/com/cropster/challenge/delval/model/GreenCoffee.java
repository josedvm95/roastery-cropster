package com.cropster.challenge.delval.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "coffees")
public class GreenCoffee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 128)
  private String name;
  
  @OneToMany(mappedBy = "greenCoffee", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
  private Set<RoastingProcess> roastingProcesses;

  public GreenCoffee() {}

  public GreenCoffee(String name) {
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

  public Set<RoastingProcess> getRoastingProcesses() {
    return roastingProcesses;
  }

  public void setRoastingProcesses(Set<RoastingProcess> roastingProcesses) {
    this.roastingProcesses = roastingProcesses;
  }
}
