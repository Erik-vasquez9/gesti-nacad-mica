package com.umg.sga.controller;

import com.umg.sga.dto.report.*;
import com.umg.sga.service.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reportes")
public class ReporteController {

    private final ReporteService reporteService;

    // GET /api/reportes/cursos-por-profesor
    @GetMapping("/cursos-por-profesor")
    public ResponseEntity<List<ReporteCursosPorProfesorDTO>> cursosPorProfesor() {
        return ResponseEntity.ok(reporteService.cursosPorProfesor());
    }

    // GET /api/reportes/promedio-por-curso
    @GetMapping("/promedio-por-curso")
    public ResponseEntity<List<ReportePromedioPorCursoDTO>> promedioPorCurso() {
        return ResponseEntity.ok(reporteService.promedioPorCurso());
    }

    // GET /api/reportes/estudiantes-por-ciclo
    @GetMapping("/estudiantes-por-ciclo")
    public ResponseEntity<List<ReporteEstudiantesPorCicloDTO>> estudiantesPorCiclo() {
        return ResponseEntity.ok(reporteService.estudiantesPorCiclo());
    }

    // GET /api/reportes/top-cursos?top=3
    @GetMapping("/top-cursos")
    public ResponseEntity<List<ReporteTopCursosPorPromedioDTO>> topCursos(
            @RequestParam(name = "top", defaultValue = "3") int top) {
        if (top <= 0) top = 3; // valor seguro
        return ResponseEntity.ok(reporteService.topCursosPorPromedio(top));
    }
}
