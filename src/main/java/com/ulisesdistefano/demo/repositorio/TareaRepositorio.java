package com.ulisesdistefano.demo.repositorio;

import com.ulisesdistefano.demo.modelo.Prioridad;
import com.ulisesdistefano.demo.modelo.Tarea;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// Repositorio para manejar operaciones CRUD sobre la entidad Tarea utilizando JdbcTemplate.
@Repository
public class TareaRepositorio {

    private final JdbcTemplate jdbc;

    public TareaRepositorio(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    // Convierte una fila SQL → objeto Tarea
    private final RowMapper<Tarea> rowMapper = (rs, rowNum) -> {
        Tarea t = new Tarea();
        t.setId(rs.getLong("id"));
        t.setTitulo(rs.getString("titulo"));
        t.setDescripcion(rs.getString("descripcion"));
        t.setCompletado(rs.getBoolean("completado"));
        t.setPrioridad(Prioridad.valueOf(rs.getString("prioridad")));
        t.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return t;
    };

    public List<Tarea> findAll() {
        return jdbc.query("SELECT * FROM tareas", rowMapper);
    }

    public Optional<Tarea> findById(Long id) {
        List<Tarea> result = jdbc.query(
                "SELECT * FROM tareas WHERE id = ?", rowMapper, id
        );
        return result.stream().findFirst();
    }

    public Tarea save(Tarea tarea) {
        if (tarea.getId() == null) {
            jdbc.update(
                    "INSERT INTO tareas (titulo, descripcion, completado, prioridad, created_at) VALUES (?, ?, ?, ?, ?)",
                    tarea.getTitulo(), tarea.getDescripcion(),
                    tarea.isCompletado(), tarea.getPrioridad().name(), // ← .name() convierte Enum a String
                    tarea.getCreatedAt()
            );
        } else {
            jdbc.update(
                    "UPDATE tareas SET titulo = ?, descripcion = ?, completado = ?, prioridad = ? WHERE id = ?",
                    tarea.getTitulo(), tarea.getDescripcion(),
                    tarea.isCompletado(), tarea.getPrioridad().name(), tarea.getId()
            );
        }
        return tarea;
    }

    public void deleteById(Long id) {
        jdbc.update("DELETE FROM tareas WHERE id = ?", id);
    }
}
