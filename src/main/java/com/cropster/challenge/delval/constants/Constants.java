package com.cropster.challenge.delval.constants;

import java.time.LocalTime;

public final class Constants {

  // Prevent instantiation
  private Constants() {}

  public static final String[] GREEN_COFFEES =
      {"Gesha Natural 91", "Aleme Wako", "Girma Bekele", "Tore Badiya", "Carmen Cecilia Montoya"};

  public static final String[] FACILITIES = {"Warehouse Austria", "Warehouse Belgium"};

  public static final String[] PRODUCTS =
      {"Aromatic", "Flavorful", "Nice Toasted", "Brown Extra", "Super Nice"};

  public static final String[] MACHINES = {"machine 001", "machine 002", "machine 003"};

  public static final Integer MIN_WEIGHT = 500;

  public static final Integer MAX_WEIGHT = 10000;

  public static final Integer START_WEIGHT_MIN = 65;

  public static final Integer START_WEIGHT_MAX = 100;

  public static final Integer MIN_DURATION = 5;

  public static final Integer MAX_DURATION = 15;

  public static final Integer MIN_CHARGE_TIME = 5;

  public static final Integer MAX_CHARGE_TIME = 10;
  
  public static final Integer MIN_WEIGHT_LOSS = 8;

  public static final Integer MAX_WEIGHT_LOSS = 15;

  public static final LocalTime OPEN_TIME = LocalTime.of(8, 0);
  
  public static final LocalTime CLOSE_TIME = LocalTime.of(19, 0);

}
