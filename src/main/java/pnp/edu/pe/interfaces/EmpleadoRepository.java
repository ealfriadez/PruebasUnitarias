package pnp.edu.pe.interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pnp.edu.pe.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{

	Optional<Empleado> findByEmail(String email);
}
