package com.ulisesdistefano.demo.dto;

import com.ulisesdistefano.demo.modelo.Prioridad;

// DTO de respuesta para las tareas
public class TareaResponseDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private boolean completado;
    private Prioridad prioridad;
    private String createdAt;

    public TareaResponseDTO( Long id, String titulo, String descripcion, boolean completado, Prioridad prioridad, String createdAt) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.completado = completado;
        this.prioridad = prioridad;
        this.createdAt = createdAt;

    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isCompletado() {
        return completado;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

}
