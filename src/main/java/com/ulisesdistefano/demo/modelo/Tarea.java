package com.ulisesdistefano.demo.modelo;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tareas")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false) private String titulo;
    private String descripcion;
    @Column(nullable = false) private boolean completado = false;
    @Column(nullable = false) private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public boolean isCompletado() {
        return completado;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
