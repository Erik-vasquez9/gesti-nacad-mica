package com.umg.sga.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "inscripcion", schema = "academico")
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inscripcion_id")
    private Long inscripcionId;

    @Column(name = "carnet", nullable = false, length = 12)
    private String carnet;

    @Column(name = "oferta_id", nullable = false)
    private Integer ofertaId;

    @Column(name = "nota_final")
    private BigDecimal notaFinal; // 0..100, puede ser null

    public Long getInscripcionId() { return inscripcionId; }
    public void setInscripcionId(Long inscripcionId) { this.inscripcionId = inscripcionId; }
    public String getCarnet() { return carnet; }
    public void setCarnet(String carnet) { this.carnet = carnet; }
    public Integer getOfertaId() { return ofertaId; }
    public void setOfertaId(Integer ofertaId) { this.ofertaId = ofertaId; }
    public BigDecimal getNotaFinal() { return notaFinal; }
    public void setNotaFinal(BigDecimal notaFinal) { this.notaFinal = notaFinal; }
}
