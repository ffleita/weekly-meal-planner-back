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
public class PlanDia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "almuerzo_receta_id")
    private Receta almuerzoReceta;
    @ManyToOne
    @JoinColumn(name = "cena_receta_id")
    private Receta cenaReceta;
    @Enumerated(EnumType.STRING)
    @Column(name = "dia_semana")
    private DiaSemana diaSemana;
    @ManyToOne()
    @JoinColumn(name = "plan_semanal_id")
    private PlanSemanal planSemanal;
}
