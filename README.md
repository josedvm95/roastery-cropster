# Cropster Challenge

This project is part of a Challenge consisting in modelling and implementing a small roasting facility.

### Requirements
1) The Schema "roasting_factory" must exist in a MySQL database
2) Add the corresponding database authentication in src/main/resources/application.properties

### Task 1
This is the ER diagram corresponding to the modeled database:
![ER Diagram](/doc/ER-diagram.png)

### Task 2
The following green coffees and facilities are added to the database.
Green coffees:
  * Gesha Natural 91, Aleme Wako, Girma Bekele, Tore Badiya, Carmen Cecilia Montoya
Facilities:
  * Warehouse Austria, Warehouse Belgium

### Notes
I made the following assumptions:
  * Each facility has its own stock of green coffee.
  * All facilities use the same 5 types of green coffee even if they keep their stock separate.
  * The initial weight of the stock of green coffee is an integer.
  * The starting weight for a roasting process is a percentage of the initial capacity. That percentage is considered to be an integer.
