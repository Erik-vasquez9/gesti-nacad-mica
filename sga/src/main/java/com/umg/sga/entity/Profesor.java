package com.umg.gestionacademica.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "profesor", schema = "academico")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Profesor {

    @Id
    @Column(name = "profesor_id", length = 10, nullable = false)
    private String profesorId;

    @NotBlank
    @Size(max = 120)
    @Column(name = "nombre_completo", length = 120, nullable = false)
    private String nombreCompleto;

    @NotBlank
    @Email
    @Size(max = 120)
    @Column(name = "email", length = 120, nullable = false, unique = true)
    private String email;
}
