package pnp.edu.pe.service;

import java.util.List;
import java.util.Optional;

import pnp.edu.pe.model.Empleado;
import pnp.edu.pe.model.dto.EmpleadoDto;

public interface EmpleadoService {

	Empleado save(Empleado e);
	List<EmpleadoDto> getAllEmployees();
	List<Empleado> getAll();
	Optional<Empleado> getById(long id);
	Empleado update(Empleado e);
	void delete(long id);
}
