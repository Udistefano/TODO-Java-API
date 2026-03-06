package com.ulisesdistefano.demo.service;
import com.ulisesdistefano.demo.dto.TareaRequestDTO;
import com.ulisesdistefano.demo.dto.TareaResponseDTO;
import com.ulisesdistefano.demo.modelo.Tarea;
import com.ulisesdistefano.demo.repositorio.TareaRepositorio;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TareaServiceImpl implements TareaService{
    private final TareaRepositorio repo;

    public TareaServiceImpl(TareaRepositorio repo) {
        this.repo = repo;
    }

    @Override
    public TareaResponseDTO obtenerPorId(Long id) {
        Tarea tarea = repo.findById(id).orElseThrow(() -> new RuntimeException("Tarea no encontrada" + id));
        return toDTO(tarea);
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
        return toDTO(repo.save(tarea));
    }

    @Override
    public TareaResponseDTO actualizar(Long id, TareaRequestDTO tareaRequest) {
        Tarea tarea = repo.findById(id).orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
        tarea.setTitulo(tareaRequest.getTitulo());
        tarea.setDescripcion(tareaRequest.getDescripcion());
        return toDTO(repo.save(tarea));
    }

    @Override
    public TareaResponseDTO completar(Long id) {
        Tarea tarea = repo.findById(id).orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
        tarea.setCompletado(true);
        return toDTO(repo.save(tarea));
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    private TareaResponseDTO toDTO(Tarea t) {
        return new TareaResponseDTO(
                t.getId(),
                t.getTitulo(),
                t.getDescripcion(),
                t.isCompletado(),
                t.getCreatedAt().toString()
        );
    }
}
