package com.cropster.challenge.delval.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.cropster.challenge.delval.dto.MachineDTO;
import com.cropster.challenge.delval.model.Machine;

@Mapper
public interface MachineMapper {
  MachineMapper INSTANCE = Mappers.getMapper(MachineMapper.class);

  @Mapping(target = "facility", ignore = true)
  Machine dtoToMachine(MachineDTO machineDto);

  @Mapping(source = "facility.id", target = "facilityId")
  MachineDTO machineToDto(Machine machine);
}
