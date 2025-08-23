package com.umg.sga.controller;

import com.umg.sga.dto.EstudianteDTO;
import com.umg.sga.entity.Estudiante;
import com.umg.sga.service.EstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;

    // POST /api/estudiantes (crea entidad)
    @PostMapping
    public ResponseEntity<Estudiante> crear(@RequestBody Estudiante estudiante) {
        Estudiante creado = estudianteService.crear(estudiante);
        return ResponseEntity
                .created(URI.create("/api/estudiantes/" + creado.getCarnet()))
                .body(creado);
    }

    // GET /api/estudiantes (DTOs) — filtro opcional apellido=...
    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> listarTodos(
            @RequestParam(value = "apellido", required = false) String apellido) {
        return ResponseEntity.ok(estudianteService.listarDTOFiltrado(apellido));
    }

    // GET /api/estudiantes/{carnet} (DTO)
    @GetMapping("/{carnet}")
    public ResponseEntity<EstudianteDTO> obtenerPorId(@PathVariable String carnet) {
        return ResponseEntity.ok(estudianteService.obtenerDTOPorId(carnet));
    }

    // PUT /api/estudiantes/{carnet} (actualiza entidad)
    @PutMapping("/{carnet}")
    public ResponseEntity<Estudiante> actualizar(@PathVariable String carnet,
                                                 @RequestBody Estudiante datos) {
        return ResponseEntity.ok(estudianteService.actualizar(carnet, datos));
    }

    // DELETE /api/estudiantes/{carnet}
    @DeleteMapping("/{carnet}")
    public ResponseEntity<Void> eliminar(@PathVariable String carnet) {
        estudianteService.eliminar(carnet);
        return ResponseEntity.noContent().build();
    }
}
