package com.ulisesdistefano.demo.dto;

import com.ulisesdistefano.demo.modelo.Prioridad;

// DTO de solicitud para crear o actualizar tareas
public class TareaRequestDTO {
    private String titulo;
    private String descripcion;
    private Prioridad prioridad;

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }
}
