package com.cropster.challenge.delval.model;

import com.cropster.challenge.delval.dto.RoastingProcessDTO;

public class RoasterResponse {
  RoastingProcessDTO roastingProcess;
  Integer responseCode;
  
  public RoasterResponse() {
  }

  public RoasterResponse(RoastingProcessDTO roastingProcess, Integer responseCode) {
    this.roastingProcess = roastingProcess;
    this.responseCode = responseCode;
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
