package com.umg.sga.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "term", schema = "academico")
public class Term {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_id")
    private Integer termId;

    @Column(name = "anio", nullable = false)
    private Short anio;

    @Column(name = "semestre", nullable = false)
    private Short semestre;

    public Integer getTermId() { return termId; }
    public void setTermId(Integer termId) { this.termId = termId; }
    public Short getAnio() { return anio; }
    public void setAnio(Short anio) { this.anio = anio; }
    public Short getSemestre() { return semestre; }
    public void setSemestre(Short semestre) { this.semestre = semestre; }
}
