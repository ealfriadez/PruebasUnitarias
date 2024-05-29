package pnp.edu.pe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pnp.edu.pe.model.Empleado;
import pnp.edu.pe.model.dto.EmpleadoDto;
import pnp.edu.pe.service.EmpleadoServiceImpl;

@RestController
@RequestMapping("/api/v1/empleados")
public class EmpleadoController {

	@Autowired
	private EmpleadoServiceImpl empleadoServiceImpl;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Empleado save(@RequestBody Empleado empleado) {
		return empleadoServiceImpl.save(empleado);
	}
	
	@GetMapping
	public List<Empleado> getAll() {
		return empleadoServiceImpl.getAll();
	}		
	
	@GetMapping("/dto")
	public List<EmpleadoDto> getAllEmpleados() {
		return empleadoServiceImpl.getAllEmployees();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Empleado> getById(@PathVariable("id") long idEmpleado) {
		return empleadoServiceImpl.getById(idEmpleado)
				.map(ResponseEntity::ok)
				.orElseGet(()-> ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{id}")	
	public ResponseEntity<Empleado> update(@PathVariable("id") long idEmpleado, @RequestBody Empleado empleado) {
		return empleadoServiceImpl.getById(idEmpleado)
				.map(empleadoEncontrado -> {
					empleadoEncontrado.setNombre(empleado.getNombre());
					empleadoEncontrado.setEmail(empleado.getEmail());
					empleadoEncontrado.setSalario(empleado.getSalario());
					
					Empleado empleadoActualizado = empleadoServiceImpl.update(empleadoEncontrado);
					return new ResponseEntity<>(empleadoActualizado, HttpStatus.OK);
				})
				.orElseGet(()-> ResponseEntity.notFound().build());			
	}
	
	@DeleteMapping("/{id}")	
	public ResponseEntity<String> delete(@PathVariable("id") long idEmpleado) {
		empleadoServiceImpl.delete(idEmpleado);
			return new ResponseEntity<>("Empleado eliminado", HttpStatus.OK);			
	}
}
