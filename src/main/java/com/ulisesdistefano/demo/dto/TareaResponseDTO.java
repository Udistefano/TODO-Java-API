package com.ulisesdistefano.demo.dto;

public class TareaResponseDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private boolean completado;
    private String createdAt;

    public TareaResponseDTO( Long id, String titulo, String descripcion, boolean completado, String createdAt) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.completado = completado;
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

}
