package com.ulisesdistefano.demo.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private int status;
    private String mensaje;
    private LocalDateTime timestamp;
    // Respuestas de error con status, mensaje y timestamp
    public ErrorResponse(int status, String mensaje) {
        this.status = status;
        this.mensaje = mensaje;
        this.timestamp = LocalDateTime.now();
    }

    public int getStatus() { return status; }
    public String getMensaje() { return mensaje; }
    public LocalDateTime getTimestamp() { return timestamp; }
}