package pnp.edu.pe.interfaces;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import pnp.edu.pe.model.Empleado;

@DataJpaTest
class EmpleadoRepositoryTest_Plus {

	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	private Empleado empleado;
	
	/*
	 * @BeforeEach void setup() { empleado = Empleado.builder()
	 * .email("moka@gmail.com") .nombre("moka") .salario(500)
	 * .hash("14587412365478") .build(); }
	 */
	
	@Test
	void testConsultarEmpleado() {
		System.out.println("Test para consultar empleados");
		
		//Empleado empleado1 = empleadoRepository.save(empleado);
		
		List<Empleado> empleados = empleadoRepository.findAll();
		
		for (Empleado empleado : empleados) {
			System.out.println("Empleados: " + empleado.getEmail());
		}
	}

}
