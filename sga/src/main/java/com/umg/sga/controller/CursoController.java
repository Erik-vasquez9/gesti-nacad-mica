package com.umg.sga.controller;

import com.umg.sga.dto.CursoDTO;
import com.umg.sga.entity.Curso;
import com.umg.sga.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    // POST /api/cursos (crea entidad)
    @PostMapping
    public ResponseEntity<Curso> crear(@RequestBody Curso curso) {
        Curso creado = cursoService.crear(curso);
        return ResponseEntity
                .created(URI.create("/api/cursos/" + creado.getCursoId()))
                .body(creado);
    }

    // GET /api/cursos (DTOs) — filtros opcionales: creditos=4, nombre=datos
    @GetMapping
    public ResponseEntity<List<CursoDTO>> listarTodos(
            @RequestParam(value = "creditos", required = false) Short creditos,
            @RequestParam(value = "nombre", required = false) String nombre) {
        return ResponseEntity.ok(cursoService.listarDTOFiltrado(creditos, nombre));
    }

    // GET /api/cursos/{id} (DTO)
    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> obtenerPorId(@PathVariable("id") String id) {
        return ResponseEntity.ok(cursoService.obtenerDTOPorId(id));
    }

    // PUT /api/cursos/{id} (actualiza entidad)
    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizar(@PathVariable("id") String id,
                                            @RequestBody Curso datos) {
        return ResponseEntity.ok(cursoService.actualizar(id, datos));
    }

    // DELETE /api/cursos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") String id) {
        cursoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
