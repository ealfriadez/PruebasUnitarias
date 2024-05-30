package pnp.edu.pe.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
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
				.email("moka@gmail.com")
				.nombre("moka")
				.salario(500)
				.hash("14587412365478")
				.build();
	}
	
	@DisplayName("Test guardar nuevo empleado")
	@Test
	void saveEmpleado(){
				
		Empleado empleado1 = empleadoRepository.save(empleado);
		
		assertThat(empleado1.getEmail()).isEqualTo("moka@gmail.com");
		assertThat(empleado1.getNombre()).isEqualTo("moka");
		assertThat(empleado1.getSalario()).isEqualTo(500);
		assertThat(empleado1.getHash()).isEqualTo("14587412365478");
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
	
	//Java Guides
	//Junit Test for saveEmployee	
	@DisplayName("Test obtener empleado")
	@Test
	public void getEmployeeTest() {		
		
		Empleado empleado1 = empleadoRepository.save(empleado);
		
		Optional<Empleado> empleadoConsultado = empleadoRepository.findByEmail(empleado1.getEmail());
		
		assertThat(empleadoConsultado).isNotEmpty();
	}
	
	@DisplayName("Test obtener todos los empleados")
	@Test
	public void getListOfEmployeeTest() {
		
		empleadoRepository.save(empleado);
		
		List<Empleado> empleados = empleadoRepository.findAll(); 
		
		assertThat(empleados.size()).isGreaterThan(0);
	}
	
}
