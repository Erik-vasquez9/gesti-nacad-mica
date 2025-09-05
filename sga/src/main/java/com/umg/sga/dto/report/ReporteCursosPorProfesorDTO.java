package com.umg.sga.dto.report;

public class ReporteCursosPorProfesorDTO {
    private final String nombreProfesor;
    private final Long totalCursos;

    public ReporteCursosPorProfesorDTO(String nombreProfesor, Long totalCursos) {
        this.nombreProfesor = nombreProfesor;
        this.totalCursos = totalCursos;
    }

    public String getNombreProfesor() { return nombreProfesor; }
    public Long getTotalCursos() { return totalCursos; }
}
