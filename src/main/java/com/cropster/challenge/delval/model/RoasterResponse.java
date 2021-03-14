package com.cropster.challenge.delval.model;

import com.cropster.challenge.delval.dto.RoastingProcessDTO;

public class RoasterResponse {
  RoastingProcessDTO roastingProcess;
  Integer facility;
  Integer machine;
  Integer responseCode;
  
  public RoasterResponse() {
  }

  public RoasterResponse(RoastingProcessDTO roastingProcess, Integer facility, Integer machine,
      Integer responseCode) {
    this.roastingProcess = roastingProcess;
    this.facility = facility;
    this.machine = machine;
    this.responseCode = responseCode;
  }

  public Integer getFacility() {
    return facility;
  }

  public void setFacility(Integer facility) {
    this.facility = facility;
  }

  public Integer getMachine() {
    return machine;
  }

  public void setMachine(Integer machine) {
    this.machine = machine;
  }

  public RoastingProcessDTO getRoastingProcess() {
    return roastingProcess;
  }

  public void setRoastingProcess(RoastingProcessDTO roastingProcess) {
    this.roastingProcess = roastingProcess;
  }

  public Integer getResponseCode() {
    return responseCode;
  }

  public void setResponseCode(Integer responseCode) {
    this.responseCode = responseCode;
  }
}
