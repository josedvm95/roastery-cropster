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

### Task 3
* Start a random roasting process:
```
curl localhost:8081/roastery/roast-random
```
It returns the following:
| Name            | Type      | Description                               | Parent          |
| --------------- | --------- | ----------------------------------------- | --------------- |
| roastingProcess | Object    |                                           | -               |
| id              | Integer   | Process identifier                        | roastingProcess |
| startWeight     | Decimal   | Initial weight                            | roastingProcess |
| endWeight       | Decimal   | End weight after the process' loss        | roastingProcess |
| startTime       | Timestamp | Initial time                              | roastingProcess |
| endTime         | Timestamp | Time when the whole process ended. <br />Including the time it takes to charge the machine | roastingProcess |
| productName     | String    | Name of the resulting product             | roastingProcess |
| greenCoffeeId   | Integer   | Green coffee used in the process          | roastingProcess |
| facility        | Integer   | Facility used in the process              | -               |
| machine         | Integer   | Machine used in the process               | -               |
| responseCode    | Integer   | Codes below                               | -               |

Response codes:
| Code            | Description                               |
| --------------- | ----------------------------------------- |
| 0               | Correct process                           |
| -1              | No facility found                         |
| -2              | No machine found                          |
| -3              | No green coffee found                     |
| -4              | Could not update the amount of coffee     |

* List the available facilities:
```
curl localhost:8081/roastery/facilities
```

* List the available machines:
```
curl localhost:8081/roastery/machines
```

* List the available green coffees:
```
curl localhost:8081/roastery/greencoffees
```

* Get information about a specific facility:
```
curl localhost:8081/roastery/facilities/{id}
```

* Get information about a specific machine:
```
curl localhost:8081/roastery/machines/{id}
```

* Get information about a specific green coffee:
```
curl localhost:8081/roastery/greencoffees/{id}
```

### Notes
I made the following assumptions:
  * Each facility has its own stock of green coffee.
  * All facilities use the same 5 types of green coffee even if they keep their stock separate.
  * The initial weight of the stock of green coffee is an integer.
  * The starting weight for a roasting process is a percentage of the initial capacity. That percentage is considered to be an integer.

### Next steps
Repeat the roast simulation until there is not enough green coffee available for roasting the required start weight.
