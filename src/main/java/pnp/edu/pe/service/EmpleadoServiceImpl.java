package pnp.edu.pe.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pnp.edu.pe.exception.NotFoundException;
import pnp.edu.pe.interfaces.EmpleadoRepository;
import pnp.edu.pe.model.Empleado;
import pnp.edu.pe.model.dto.EmpleadoDto;
import pnp.edu.pe.model.mapper.EmpleadoMapper;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{

	@Autowired
	EmpleadoRepository empleadoRepository;
	
	@Override
	public Empleado save(Empleado empleado) {
		Optional<Empleado> nuevoEmpleado = empleadoRepository.findByEmail(empleado.getEmail());
		if (nuevoEmpleado.isPresent()) {
			throw new NotFoundException("Ya existe un empleado con email:" + empleado.getEmail());
		}
		return empleadoRepository.save(empleado);
	}

	@Override
	public List<EmpleadoDto> getAllEmployees() {
		
		List<Empleado> empleados = empleadoRepository.findAll();
		
		List<EmpleadoDto> empleadoDtos = empleados.stream().map(
				empleado -> EmpleadoMapper.mapper.empleadoToEmpleadoDto(empleado)).collect(Collectors.toList());
				
		return empleadoDtos;
	}
	
	@Override
	public List<Empleado> getAll() {		
		return empleadoRepository.findAll();
	}

	@Override
	public Optional<Empleado> getById(long id) {
		return empleadoRepository.findById(id);
	}

	@Override
	public Empleado update(Empleado empleado) {
		Optional<Empleado> actualizarEmpleado = empleadoRepository.findByEmail(empleado.getEmail());
		if (!actualizarEmpleado.isPresent()) {
			throw new NotFoundException("No existe un empleado con email:" + empleado.getEmail());
		}
		return empleadoRepository.save(empleado);
	}

	@Override
	public void delete(long id) {
		empleadoRepository.deleteById(id);
	}
}
