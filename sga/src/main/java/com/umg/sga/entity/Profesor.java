package com.umg.sga.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "profesor", schema = "academico")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profesor {

    @Id
    @Column(name = "profesor_id", length = 10, nullable = false)
    private String profesorId;

    @Column(name = "nombre_completo", length = 120, nullable = false)
    private String nombreCompleto;

    @Column(name = "email", length = 120, nullable = false, unique = true)
    private String email;
}
