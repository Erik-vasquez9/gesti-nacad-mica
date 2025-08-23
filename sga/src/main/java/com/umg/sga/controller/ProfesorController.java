package com.umg.sga.controller;

import com.umg.sga.dto.ProfesorDTO;
import com.umg.sga.entity.Profesor;
import com.umg.sga.service.ProfesorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profesores")
public class ProfesorController {

    private final ProfesorService profesorService;

    // POST /api/profesores (crea entidad)
    @PostMapping
    public ResponseEntity<Profesor> crear(@RequestBody Profesor profesor) {
        Profesor creado = profesorService.crear(profesor);
        return ResponseEntity
                .created(URI.create("/api/profesores/" + creado.getProfesorId()))
                .body(creado);
    }

    // GET /api/profesores  (DTOs) — filtro opcional q=texto (nombre/email)
    @GetMapping
    public ResponseEntity<List<ProfesorDTO>> listarTodos(
            @RequestParam(value = "q", required = false) String q) {
        return ResponseEntity.ok(profesorService.listarDTOFiltrado(q));
    }

    // GET /api/profesores/{id}  (DTO)
    @GetMapping("/{id}")
    public ResponseEntity<ProfesorDTO> obtenerPorId(@PathVariable("id") String id) {
        return ResponseEntity.ok(profesorService.obtenerDTOPorId(id));
    }

    // PUT /api/profesores/{id} (actualiza entidad)
    @PutMapping("/{id}")
    public ResponseEntity<Profesor> actualizar(@PathVariable("id") String id,
                                               @RequestBody Profesor datos) {
        return ResponseEntity.ok(profesorService.actualizar(id, datos));
    }

    // DELETE /api/profesores/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") String id) {
        profesorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
