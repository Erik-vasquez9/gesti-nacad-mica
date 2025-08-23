package com.umg.sga.repository;

import com.umg.sga.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EstudianteRepository extends JpaRepository<Estudiante, String> {

    // Filtro por apellido (contiene, case-insensitive)
    List<Estudiante> findByApellidoIgnoreCaseContaining(String apellido);
}
