package com.cropster.challenge.delval.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.cropster.challenge.delval.dto.FacilityDTO;
import com.cropster.challenge.delval.model.Facility;

@Mapper
public interface FacilityMapper {
  FacilityMapper INSTANCE = Mappers.getMapper(FacilityMapper.class);
  
  @Mapping(target = "machines", ignore = true)
  Facility dtoToFacility(FacilityDTO facilityDto);
  
  FacilityDTO facilityToDto(Facility facility);
}
