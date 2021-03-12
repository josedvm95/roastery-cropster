package com.cropster.challenge.delval.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.cropster.challenge.delval.dto.GreenCoffeeDTO;
import com.cropster.challenge.delval.model.GreenCoffee;

@Mapper
public interface GreenCoffeeMapper {
  GreenCoffeeMapper INSTANCE = Mappers.getMapper(GreenCoffeeMapper.class);
  
  GreenCoffeeDTO greenCoffeeToDto(GreenCoffee greenCoffee);
}
