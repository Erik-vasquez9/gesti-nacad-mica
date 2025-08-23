package com.umg.sga.repository;

import com.umg.sga.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProfesorRepository extends JpaRepository<Profesor, String> {
    boolean existsByEmail(String email);

    // Filtro por nombre o email (contiene, case-insensitive)
    List<Profesor> findByNombreCompletoIgnoreCaseContainingOrEmailIgnoreCaseContaining(String nombre, String email);
}
