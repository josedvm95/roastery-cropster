package com.cropster.challenge.delval.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.cropster.challenge.delval.dto.RoastingProcessDTO;
import com.cropster.challenge.delval.model.RoastingProcess;

@Mapper
public interface RoastingProcessMapper {
  RoastingProcessMapper INSTANCE = Mappers.getMapper(RoastingProcessMapper.class);
  
  @Mapping(source = "greenCoffee.id", target = "greenCoffeeId")
  RoastingProcessDTO roastingProcessToDto(RoastingProcess roastingProcess);
}
