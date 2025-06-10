package ffleitas.back.dtos.ingredientes;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class IngredienteDTO {

    private int id;
    private String nombre;
}
