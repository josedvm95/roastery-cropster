package com.cropster.challenge.delval.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.cropster.challenge.delval.dto.StockDTO;
import com.cropster.challenge.delval.model.Stock;

@Mapper
public interface StockMapper {
  StockMapper INSTANCE = Mappers.getMapper(StockMapper.class);
  
  StockDTO stockToDto(Stock stock);
}
