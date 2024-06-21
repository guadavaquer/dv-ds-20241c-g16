package ar.edu.davinci.dv_ds_20241c_g16.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ar.edu.davinci.dv_ds_20241c_g16.controller.request.PrendaInsertRequest;
import ar.edu.davinci.dv_ds_20241c_g16.controller.request.PrendaUpdateRequest;
import ar.edu.davinci.dv_ds_20241c_g16.controller.response.PrendaResponse;
import ar.edu.davinci.dv_ds_20241c_g16.domain.Prenda;
@Mapper
public interface PrendaMapper {
	PrendaMapper instance = Mappers.getMapper(PrendaMapper.class);
	PrendaResponse mapToPrendaResponse(Prenda prenda);
	Prenda matToPrenda(PrendaInsertRequest prendaDto);
	Prenda matToPrenda(PrendaUpdateRequest prendaDto);
}
