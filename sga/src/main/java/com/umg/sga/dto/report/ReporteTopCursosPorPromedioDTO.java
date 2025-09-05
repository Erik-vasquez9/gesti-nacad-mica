package com.umg.sga.dto.report;

public class ReporteTopCursosPorPromedioDTO {
    private final String cursoId;
    private final String nombreCurso;
    private final Double promedioNota;

    public ReporteTopCursosPorPromedioDTO(String cursoId, String nombreCurso, Double promedioNota) {
        this.cursoId = cursoId;
        this.nombreCurso = nombreCurso;
        this.promedioNota = promedioNota;
    }

    public String getCursoId() { return cursoId; }
    public String getNombreCurso() { return nombreCurso; }
    public Double getPromedioNota() { return promedioNota; }
}
