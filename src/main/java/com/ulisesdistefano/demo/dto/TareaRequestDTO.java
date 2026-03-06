package com.ulisesdistefano.demo.dto;

public class TareaRequestDTO {
    private String titulo;
    private String descripcion;

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
