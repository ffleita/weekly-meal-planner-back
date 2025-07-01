package ffleitas.back.dtos.planes;

import ffleitas.back.dtos.recetas.RecetaDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PlanDiaDTO {
    private String diaSemana;
    private RecetaDTO almuerzoReceta;
    private RecetaDTO cenaReceta;
}
