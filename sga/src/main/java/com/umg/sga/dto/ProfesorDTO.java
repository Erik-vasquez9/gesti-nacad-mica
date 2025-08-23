package com.umg.sga.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfesorDTO {
    private String profesorId;
    private String nombreCompleto;
    private String email; // lo exponemos (no es sensible en este caso)
}
