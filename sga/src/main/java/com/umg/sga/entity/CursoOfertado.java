package com.umg.sga.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "curso_ofertado", schema = "academico")
public class CursoOfertado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oferta_id")
    private Integer ofertaId;

    @Column(name = "curso_id", nullable = false, length = 10)
    private String cursoId;

    @Column(name = "term_id", nullable = false)
    private Integer termId;

    @Column(name = "profesor_id", nullable = false, length = 10)
    private String profesorId;

    public Integer getOfertaId() { return ofertaId; }
    public void setOfertaId(Integer ofertaId) { this.ofertaId = ofertaId; }
    public String getCursoId() { return cursoId; }
    public void setCursoId(String cursoId) { this.cursoId = cursoId; }
    public Integer getTermId() { return termId; }
    public void setTermId(Integer termId) { this.termId = termId; }
    public String getProfesorId() { return profesorId; }
    public void setProfesorId(String profesorId) { this.profesorId = profesorId; }
}
