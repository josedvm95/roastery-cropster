package com.cropster.challenge.delval.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.cropster.challenge.delval.dto.FacilityDTO;
import com.cropster.challenge.delval.model.Facility;

@Mapper
public interface FacilityMapper {
  FacilityMapper INSTANCE = Mappers.getMapper(FacilityMapper.class);
  
  FacilityDTO facilityToDto(Facility facility);
}
