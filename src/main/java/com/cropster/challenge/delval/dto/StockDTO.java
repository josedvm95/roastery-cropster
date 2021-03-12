package com.cropster.challenge.delval.dto;

import java.math.BigDecimal;
import com.cropster.challenge.delval.model.StockPK;

public class StockDTO {
  private StockPK id;
  private BigDecimal amount;
  
  public StockDTO() {
  }

  public StockDTO(StockPK id, BigDecimal amount) {
    this.id = id;
    this.amount = amount;
  }

  public StockPK getId() {
    return id;
  }

  public void setId(StockPK id) {
    this.id = id;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  @Override
  public String toString() {
    return "StockDTO [id=" + id + ", amount=" + amount + "]";
  }
}
