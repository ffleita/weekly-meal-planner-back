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
public class IngredienteReceta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY,  optional = false)
    @JoinColumn(name = "ingrediente_id", nullable = false)
    private Ingrediente ingrediente;
    private Integer cantidad;
    @ManyToOne(fetch = FetchType.LAZY,  optional = false)
    @JoinColumn(name = "medida_id", nullable = false)
    private Medida medida;
    @ManyToOne(fetch = FetchType.LAZY,  optional = false)
    @JoinColumn(name = "receta_id", nullable = false)
    private Receta receta;
}
