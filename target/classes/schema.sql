CREATE TABLE IF NOT EXISTS tareas (
    id          INTEGER PRIMARY KEY AUTOINCREMENT,
    titulo      TEXT    NOT NULL,
    descripcion TEXT,
    completado  INTEGER NOT NULL DEFAULT 0,
    created_at  TEXT    NOT NULL
);