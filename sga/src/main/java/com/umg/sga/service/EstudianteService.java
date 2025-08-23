package com.umg.sga.service;

import com.umg.sga.dto.EstudianteDTO;
import com.umg.sga.entity.Estudiante;
import com.umg.sga.exception.NotFoundException;
import com.umg.sga.repository.EstudianteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;

    // ---------- CRUD ----------
    public Estudiante crear(Estudiante est) {
        return estudianteRepository.save(est);
    }

    public List<Estudiante> listarTodos() {
        return estudianteRepository.findAll();
    }

    public Estudiante obtenerPorId(String carnet) {
        return estudianteRepository.findById(carnet)
                .orElseThrow(() -> new NotFoundException("Estudiante no encontrado: " + carnet));
    }

    public Estudiante actualizar(String carnet, Estudiante datos) {
        Estudiante existente = obtenerPorId(carnet);
        existente.setNombre(datos.getNombre());
        existente.setApellido(datos.getApellido());
        existente.setFechaNacimiento(datos.getFechaNacimiento());
        return estudianteRepository.save(existente);
    }

    public void eliminar(String carnet) {
        Estudiante existente = obtenerPorId(carnet);
        estudianteRepository.delete(existente);
    }

    // ---------- Conversión a DTO (edad) ----------
    private EstudianteDTO convertirAEstudianteDTO(Estudiante e) {
        Integer edad = null;
        LocalDate fn = e.getFechaNacimiento();
        if (fn != null) {
            edad = Period.between(fn, LocalDate.now()).getYears();
        }
        return EstudianteDTO.builder()
                .carnet(e.getCarnet())
                .nombre(e.getNombre())
                .apellido(e.getApellido())
                .edad(edad)
                .build();
    }

    // ---------- DTOs sin/ con filtro ----------
    public List<EstudianteDTO> listarTodosDTO() {
        return estudianteRepository.findAll()
                .stream().map(this::convertirAEstudianteDTO).collect(Collectors.toList());
    }

    public EstudianteDTO obtenerDTOPorId(String carnet) {
        return convertirAEstudianteDTO(obtenerPorId(carnet));
    }

    // Filtro por apellido
    public List<EstudianteDTO> listarDTOFiltrado(String apellido) {
        List<Estudiante> datos = (apellido == null || apellido.isBlank())
                ? estudianteRepository.findAll()
                : estudianteRepository.findByApellidoIgnoreCaseContaining(apellido);
        return datos.stream().map(this::convertirAEstudianteDTO).toList();
    }
}
