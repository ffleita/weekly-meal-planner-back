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
public class PlanSemanal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    @Column(name = "borrado_logico", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean borradoLogico = false;
}
