package com.umg.sga.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "curso", schema = "academico")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Curso {

    @Id
    @Column(name = "curso_id", length = 10, nullable = false)
    private String cursoId;

    @Column(name = "nombre", length = 120, nullable = false)
    private String nombre;

    @Column(name = "creditos", nullable = false)
    private Short creditos;

    // Un curso puede tener 0..1 prerrequisito (autorrelación)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_prerrequisito_id", referencedColumnName = "curso_id")
    private Curso cursoPrerrequisito;
}
