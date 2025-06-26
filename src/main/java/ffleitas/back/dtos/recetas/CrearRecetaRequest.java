package ffleitas.back.dtos.recetas;

import ffleitas.back.dtos.ingredientereceta.IngredienteRecetaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrearRecetaRequest {
    private String nombre;
    private String pasos;
    private List<IngredienteRecetaDTO> ingredientesReceta;
}
