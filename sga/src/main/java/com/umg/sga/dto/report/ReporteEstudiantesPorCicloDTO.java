package com.umg.sga.dto.report;

public class ReporteEstudiantesPorCicloDTO {
    private final String ciclo;       // Ej.: "Ciclo 2025"
    private final Long totalEstudiantes;

    public ReporteEstudiantesPorCicloDTO(String ciclo, Long totalEstudiantes) {
        this.ciclo = ciclo;
        this.totalEstudiantes = totalEstudiantes;
    }

    public String getCiclo() { return ciclo; }
    public Long getTotalEstudiantes() { return totalEstudiantes; }
}
