package com.cropster.challenge.delval.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "machines", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "facility_id"}))
public class Machine {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 128)
  private String name;

  @Column(nullable = false)
  private Integer capacity;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "facility_id", nullable = true)
  private Facility facility;

  public Machine() {}

  public Machine(String name, Integer capacity, Facility facility) {
    this.name = name;
    this.capacity = capacity;
    this.facility = facility;
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

  public Facility getFacility() {
    facility.setMachines(null);
    return facility;
  }

  public void setFacility(Facility facility) {
    this.facility = facility;
  }
}
