package com.umg.sga.service;

import com.umg.sga.dto.ProfesorDTO;
import com.umg.sga.entity.Profesor;
import com.umg.sga.exception.NotFoundException;
import com.umg.sga.repository.ProfesorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfesorService {

    private final ProfesorRepository profesorRepository;

    // ---------- CRUD ----------
    public Profesor crear(Profesor profesor) {
        if (profesorRepository.existsByEmail(profesor.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        return profesorRepository.save(profesor);
    }

    public List<Profesor> listarTodos() {
        return profesorRepository.findAll();
    }

    public Profesor obtenerPorId(String profesorId) {
        return profesorRepository.findById(profesorId)
                .orElseThrow(() -> new NotFoundException("Profesor no encontrado: " + profesorId));
    }

    public Profesor actualizar(String profesorId, Profesor datos) {
        Profesor existente = obtenerPorId(profesorId);
        existente.setNombreCompleto(datos.getNombreCompleto());
        existente.setEmail(datos.getEmail());
        return profesorRepository.save(existente);
    }

    public void eliminar(String profesorId) {
        Profesor existente = obtenerPorId(profesorId);
        profesorRepository.delete(existente);
    }

    // ---------- Conversión a DTO ----------
    private ProfesorDTO convertirAProfesorDTO(Profesor p) {
        return ProfesorDTO.builder()
                .profesorId(p.getProfesorId())
                .nombreCompleto(p.getNombreCompleto())
                .email(p.getEmail())
                .build();
    }

    // ---------- DTOs sin/ con filtro ----------
    public List<ProfesorDTO> listarTodosDTO() {
        return profesorRepository.findAll()
                .stream().map(this::convertirAProfesorDTO).collect(Collectors.toList());
    }

    public ProfesorDTO obtenerDTOPorId(String profesorId) {
        return convertirAProfesorDTO(obtenerPorId(profesorId));
    }

    // Filtro por q (nombre o email)
    public List<ProfesorDTO> listarDTOFiltrado(String q) {
        List<Profesor> datos = (q == null || q.isBlank())
                ? profesorRepository.findAll()
                : profesorRepository.findByNombreCompletoIgnoreCaseContainingOrEmailIgnoreCaseContaining(q, q);
        return datos.stream().map(this::convertirAProfesorDTO).toList();
    }
}
