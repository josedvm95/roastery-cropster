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
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "facilities")
public class Facility {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 128)
  private String name;

  @JsonIgnore
  @OneToMany(mappedBy = "facility", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
  private Set<Machine> machines;

  public Facility() {}

  public Facility(String name) {
    this.name = name;
  }
  
  public Facility(Integer id, String name) {
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

  public Set<Machine> getMachines() {
    return machines;
  }

  public void setMachines(Set<Machine> machines) {
    this.machines = machines;
  }
}
