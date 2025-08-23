package com.umg.sga.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CursoDTO {
    private String cursoId;
    private String nombre;
    private Short creditos;
    private String cursoPrerrequisitoId; // solo el ID del prerrequisito (si existe)
}
