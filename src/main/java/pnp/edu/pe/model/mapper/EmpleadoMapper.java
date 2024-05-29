package pnp.edu.pe.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import pnp.edu.pe.model.Empleado;
import pnp.edu.pe.model.dto.EmpleadoDto;

@Mapper
public interface EmpleadoMapper {

	EmpleadoMapper mapper = Mappers.getMapper(EmpleadoMapper.class);
	
	@Mapping(source = "hash", target = "hash")
	EmpleadoDto empleadoToEmpleadoDto(Empleado empleado);
	
	@Mapping(source = "hash", target = "hash")
	Empleado empleadoDtoToEmpleado(EmpleadoDto empleadoDto);
}
