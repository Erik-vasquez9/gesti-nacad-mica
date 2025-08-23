package com.umg.sga.service;

import com.umg.sga.dto.CursoDTO;
import com.umg.sga.entity.Curso;
import com.umg.sga.exception.NotFoundException;
import com.umg.sga.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;

    // ---------- CRUD ----------
    public Curso crear(Curso curso) {
        return cursoRepository.save(curso);
    }

    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }

    public Curso obtenerPorId(String cursoId) {
        return cursoRepository.findById(cursoId)
                .orElseThrow(() -> new NotFoundException("Curso no encontrado: " + cursoId));
    }

    public Curso actualizar(String cursoId, Curso datos) {
        Curso existente = obtenerPorId(cursoId);
        existente.setNombre(datos.getNombre());
        existente.setCreditos(datos.getCreditos());
        existente.setCursoPrerrequisito(datos.getCursoPrerrequisito()); // puede ser null
        return cursoRepository.save(existente);
    }

    public void eliminar(String cursoId) {
        Curso existente = obtenerPorId(cursoId);
        cursoRepository.delete(existente);
    }

    // ---------- Conversión a DTO ----------
    private CursoDTO convertirACursoDTO(Curso c) {
        String prereqId = (c.getCursoPrerrequisito() != null)
                ? c.getCursoPrerrequisito().getCursoId()
                : null;

        return CursoDTO.builder()
                .cursoId(c.getCursoId())
                .nombre(c.getNombre())
                .creditos(c.getCreditos())
                .cursoPrerrequisitoId(prereqId)
                .build();
    }

    // ---------- DTOs sin/ con filtro ----------
    public List<CursoDTO> listarTodosDTO() {
        return cursoRepository.findAll()
                .stream().map(this::convertirACursoDTO).collect(Collectors.toList());
    }

    public CursoDTO obtenerDTOPorId(String cursoId) {
        return convertirACursoDTO(obtenerPorId(cursoId));
    }

    // Filtros: creditos y/o nombre
    public List<CursoDTO> listarDTOFiltrado(Short creditos, String nombre) {
        List<Curso> base;

        if (creditos != null && (nombre != null && !nombre.isBlank())) {
            // Ambos filtros: intersección
            List<Curso> porCreditos = cursoRepository.findByCreditos(creditos);
            List<Curso> porNombre = cursoRepository.findByNombreIgnoreCaseContaining(nombre);
            base = porCreditos.stream()
                    .filter(c1 -> porNombre.stream().anyMatch(c2 -> c2.getCursoId().equals(c1.getCursoId())))
                    .toList();
        } else if (creditos != null) {
            base = cursoRepository.findByCreditos(creditos);
        } else if (nombre != null && !nombre.isBlank()) {
            base = cursoRepository.findByNombreIgnoreCaseContaining(nombre);
        } else {
            base = cursoRepository.findAll();
        }

        return base.stream().map(this::convertirACursoDTO).toList();
    }
}
