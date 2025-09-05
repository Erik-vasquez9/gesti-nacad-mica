package com.umg.sga.dto.report;

public class ReportePromedioPorCursoDTO {
    private final String nombreCurso;
    private final Double promedioNota;

    public ReportePromedioPorCursoDTO(String nombreCurso, Double promedioNota) {
        this.nombreCurso = nombreCurso;
        this.promedioNota = promedioNota;
    }

    public String getNombreCurso() { return nombreCurso; }
    public Double getPromedioNota() { return promedioNota; }
}
