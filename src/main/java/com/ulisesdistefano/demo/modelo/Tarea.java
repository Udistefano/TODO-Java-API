package com.ulisesdistefano.demo.modelo;

import java.time.LocalDateTime;

// clase tarea con getters y setters
public class Tarea {

    private Long id;
    private String titulo;
    private String descripcion;
    private boolean completado;
    private LocalDateTime createdAt;

    // Getters
    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
    public boolean isCompletado() { return completado; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setCompletado(boolean completado) { this.completado = completado; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}