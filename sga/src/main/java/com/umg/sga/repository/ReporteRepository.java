package com.umg.sga.repository;

import com.umg.sga.dto.report.*;
import com.umg.sga.entity.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repositorio dedicado a reportes.
 * Extendemos de JpaRepository con la entidad Inscripcion (cualquiera válida)
 * solo para tener el bean y acceder a @Query.
 */
public interface ReporteRepository extends JpaRepository<Inscripcion, Long> {

    // ==============================
    // Reporte 1: cursos por profesor
    // ==============================
    @Query("""
        SELECT NEW com.umg.sga.dto.report.ReporteCursosPorProfesorDTO(
            p.nombreCompleto,
            COUNT(DISTINCT co.cursoId)
        )
        FROM Profesor p
        JOIN CursoOfertado co ON co.profesorId = p.profesorId
        GROUP BY p.nombreCompleto
        ORDER BY p.nombreCompleto ASC
    """)
    List<ReporteCursosPorProfesorDTO> cursosPorProfesor();

    // ==========================================
    // Reporte 2: Promedio de nota final por curso
    // ==========================================
    @Query("""
        SELECT NEW com.umg.sga.dto.report.ReportePromedioPorCursoDTO(
            c.nombre,
            AVG(i.notaFinal)
        )
        FROM Inscripcion i
        JOIN CursoOfertado co ON i.ofertaId = co.ofertaId
        JOIN Curso c ON co.cursoId = c.cursoId
        WHERE i.notaFinal IS NOT NULL
        GROUP BY c.nombre
        ORDER BY c.nombre ASC
    """)
    List<ReportePromedioPorCursoDTO> promedioPorCurso();

    // ===========================================
    // Reporte 3: Estudiantes por ciclo (por año)
    // ===========================================
    @Query("""
        SELECT NEW com.umg.sga.dto.report.ReporteEstudiantesPorCicloDTO(
            CONCAT('Ciclo ', t.anio),
            COUNT(DISTINCT i.carnet)
        )
        FROM Inscripcion i
        JOIN CursoOfertado co ON i.ofertaId = co.ofertaId
        JOIN Term t ON co.termId = t.termId
        GROUP BY t.anio
        ORDER BY t.anio ASC
    """)
    List<ReporteEstudiantesPorCicloDTO> estudiantesPorCiclo();

    // ==========================================================
    // Reporte 4: Top cursos con mayor promedio (usar Pageable)
    // ==========================================================
    @Query("""
        SELECT NEW com.umg.sga.dto.report.ReporteTopCursosPorPromedioDTO(
            c.cursoId,
            c.nombre,
            AVG(i.notaFinal)
        )
        FROM Inscripcion i
        JOIN CursoOfertado co ON i.ofertaId = co.ofertaId
        JOIN Curso c ON co.cursoId = c.cursoId
        WHERE i.notaFinal IS NOT NULL
        GROUP BY c.cursoId, c.nombre
        ORDER BY AVG(i.notaFinal) DESC
    """)
    List<ReporteTopCursosPorPromedioDTO> topCursosPorPromedio(Pageable pageable);
}
