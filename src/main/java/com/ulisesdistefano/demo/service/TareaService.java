package com.ulisesdistefano.demo.service;

import com.ulisesdistefano.demo.dto.TareaRequestDTO;
import com.ulisesdistefano.demo.dto.TareaResponseDTO;
import java.util.List;

// Interfaz del servicio de tareas que define las operaciones CRUD y completar tarea
public interface TareaService {
    List<TareaResponseDTO> obtenerTodas();
    TareaResponseDTO obtenerPorId(Long id);
    TareaResponseDTO crear(TareaRequestDTO tareaRequest);
    TareaResponseDTO actualizar(Long id, TareaRequestDTO tareaRequest);
    void eliminar(Long id);
    TareaResponseDTO completar(Long id);
}
