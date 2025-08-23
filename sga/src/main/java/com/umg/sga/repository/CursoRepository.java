package com.umg.sga.repository;

import com.umg.sga.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, String> {

    // Filtros
    List<Curso> findByCreditos(Short creditos);
    List<Curso> findByNombreIgnoreCaseContaining(String nombre);
}
