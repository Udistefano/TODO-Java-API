package com.ulisesdistefano.demo.controller;

import com.ulisesdistefano.demo.dto.TareaRequestDTO;
import com.ulisesdistefano.demo.dto.TareaResponseDTO;
import com.ulisesdistefano.demo.modelo.Tarea;
import com.ulisesdistefano.demo.repositorio.TareaRepositorio;
import com.ulisesdistefano.demo.service.TareaService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {
    private final TareaService service;

    public TareaController(TareaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity <List<TareaResponseDTO>> obtenerTodas() {
        return ResponseEntity.ok(service.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TareaResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<TareaResponseDTO> crear(@RequestBody TareaRequestDTO tareaRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(tareaRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TareaResponseDTO> actualizar(@PathVariable Long id, @RequestBody TareaRequestDTO tareaRequest) {
        return ResponseEntity.ok(service.actualizar(id, tareaRequest));
    }

    @PatchMapping("/{id}/completar")
    public ResponseEntity<TareaResponseDTO> completar(@PathVariable Long id) {
        return ResponseEntity.ok(service.completar(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
