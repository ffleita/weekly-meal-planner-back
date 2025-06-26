package ffleitas.back.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Receta {

    public Receta(String nombre, String pasos) {
        this.nombre = nombre;
        this.pasos = pasos;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Column(columnDefinition = "TEXT")
    private String pasos;
    @Column(name = "borrado_logico", nullable = false)
    private boolean borradoLogico = false;
}
