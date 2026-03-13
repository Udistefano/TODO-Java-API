package com.ulisesdistefano.demo.service;
import com.ulisesdistefano.demo.dto.TareaRequestDTO;
import com.ulisesdistefano.demo.dto.TareaResponseDTO;
import com.ulisesdistefano.demo.exception.TareaNotFoundException;
import com.ulisesdistefano.demo.modelo.Prioridad;
import com.ulisesdistefano.demo.modelo.Tarea;
import com.ulisesdistefano.demo.repositorio.TareaRepositorio;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional; // Optional con metodo ispresent hace que lo busque directamente en la base, sin usar un for ni nada

/*
 * Implementación de la lógica de negocio para manejar tareas.
 * Se encarga de interactuar con el repositorio y transformar entidades a DTOs.
 */
@Service
public class TareaServiceImpl implements TareaService{
    private final TareaRepositorio repo;

    public TareaServiceImpl(TareaRepositorio repo) {
        this.repo = repo;
    }

    @Override
    public TareaResponseDTO obtenerPorId(Long id) {
        Optional<Tarea> resultado = repo.findById(id);
        if (resultado.isPresent()) {
            return toDTO(resultado.get());
        } else {
            throw new TareaNotFoundException(id);
        }
    }

    @Override
    public List<TareaResponseDTO> obtenerTodas() {
        return repo.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public TareaResponseDTO crear(TareaRequestDTO tareaRequest) {
        Tarea tarea = new Tarea();
        tarea.setTitulo(tareaRequest.getTitulo());
        tarea.setDescripcion(tareaRequest.getDescripcion());
        tarea.setCompletado(false);
        tarea.setCreatedAt(LocalDateTime.now());
        if (tareaRequest.getPrioridad() != null) {
            tarea.setPrioridad(tareaRequest.getPrioridad());
        } else {
            tarea.setPrioridad(Prioridad.MEDIA);
        }
        return toDTO(repo.save(tarea));
    }

    @Override
    public TareaResponseDTO actualizar(Long id, TareaRequestDTO tareaRequest) {
        Optional <Tarea> resultado = repo.findById(id);
        if (resultado.isEmpty()) {
            Tarea tarea = resultado.get();
            tarea.setTitulo(tareaRequest.getTitulo());
            tarea.setDescripcion(tareaRequest.getDescripcion());
            return toDTO(repo.save(tarea));
        } else {
            throw new TareaNotFoundException(id);
        }
    }

    @Override
    public TareaResponseDTO completar(Long id) {
        Optional<Tarea> resultado = repo.findById(id);
        if (resultado.isPresent()) {
            Tarea tarea = resultado.get();
            tarea.setCompletado(true);
            return toDTO(repo.save(tarea));
        } else {
            throw new TareaNotFoundException(id);
        }
    }

    @Override
    public void eliminar(Long id) {
        Optional<Tarea> resultado = repo.findById(id);
        if (resultado.isPresent()) {
            repo.deleteById(id);
        } else {
            throw new TareaNotFoundException(id);
        }
    }

    private TareaResponseDTO toDTO(Tarea t) {
        return new TareaResponseDTO(
                t.getId(),
                t.getTitulo(),
                t.getDescripcion(),
                t.isCompletado(),
                t.getPrioridad(),
                t.getCreatedAt().toString()
        );
    }
}
