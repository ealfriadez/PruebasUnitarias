package pnp.edu.pe.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import pnp.edu.pe.interfaces.EmpleadoRepository;
import pnp.edu.pe.model.Empleado;

@DataJpaTest
public class EmpleadoRepositoryTest {

	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	private Empleado empleado;
	
	@BeforeEach
	void setup() {
		empleado = Empleado.builder()
				.email("ealfriadez@gmail.com")
				.nombre("Eleazar Alfredo")
				.salario(1000)
				.build();
	}
	
	@DisplayName("Test guardar nuevo empleado")
	@Test
	void saveEmpleado(){
				
		Empleado empleado1 = empleadoRepository.save(empleado);
		
		assertThat(empleado1.getEmail()).isEqualTo("ealfriadez@gmail.com");
		assertThat(empleado1.getNombre()).isEqualTo("Eleazar Alfredo");
		assertThat(empleado1.getSalario()).isEqualTo(1000);
		assertThat(empleado1.getId()).isGreaterThan(0);
	}
	
	@DisplayName("Test actualizar empleado")
	@Test
	void updateEmpleado(){		
		
		Empleado empleado1 = empleadoRepository.save(empleado);
		
		empleado1.setNombre("Sebastian Alfredo");
		empleado1.setSalario(5500);
		
		Empleado empleadoActualizado = empleadoRepository.save(empleado1);
				
		assertThat(empleadoActualizado.getNombre()).isEqualTo("Sebastian Alfredo");
		assertThat(empleadoActualizado.getSalario()).isEqualTo(5500);		
	}
	
	@DisplayName("Test eliminar empleado")
	@Test
	void deleteEmpleado(){		
		
		Empleado empleado1 = empleadoRepository.save(empleado);
		
		Optional<Empleado> empleadoConsultado = empleadoRepository.findById(empleado1.getId());
		
		empleadoRepository.deleteById(empleado1.getId());		
		
		Optional<Empleado> empleadoEliminado = empleadoRepository.findById(empleado1.getId());
				
		assertThat(empleadoConsultado).isNotEmpty();
		assertThat(empleadoEliminado).isEmpty();		
	}
}
