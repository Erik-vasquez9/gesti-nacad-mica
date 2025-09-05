package com.umg.sga.service;

import com.umg.sga.dto.report.*;
import com.umg.sga.repository.ReporteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReporteService {

    private final ReporteRepository reporteRepository;

    // Reporte 1: número total de cursos que imparte cada profesor
    public List<ReporteCursosPorProfesorDTO> cursosPorProfesor() {
        return reporteRepository.cursosPorProfesor();
    }

    // Reporte 2: nota promedio para cada curso
    public List<ReportePromedioPorCursoDTO> promedioPorCurso() {
        return reporteRepository.promedioPorCurso();
    }

    // Reporte 3: cantidad de estudiantes por ciclo (año)
    public List<ReporteEstudiantesPorCicloDTO> estudiantesPorCiclo() {
        return reporteRepository.estudiantesPorCiclo();
    }

    // Reporte 4: top N cursos con mayor promedio
    public List<ReporteTopCursosPorPromedioDTO> topCursosPorPromedio(int top) {
        // PageRequest.of(0, top) limita los resultados
        return reporteRepository.topCursosPorPromedio(PageRequest.of(0, top));
    }
}
