package com.umg.sga.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstudianteDTO {
    private String carnet;
    private String nombre;
    private String apellido;
    private Integer edad; // la calcularemos en el Servicio a partir de fecha_nacimiento
}
