CREATE TABLE IF NOT EXISTS tareas (
    id          SERIAL PRIMARY KEY,
    titulo      TEXT    NOT NULL,
    descripcion TEXT,
    completado  BOOLEAN NOT NULL DEFAULT FALSE,
    prioridad   TEXT    NOT NULL DEFAULT 'MEDIA',
    created_at  TIMESTAMP NOT NULL
);