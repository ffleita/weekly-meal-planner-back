package ffleitas.back.dtos.recetas;

import ffleitas.back.dtos.ingredientereceta.IngredienteRecetaDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrearRecetaRequest {
    @NotNull(message = "El nombre de la receta no puede ser vacio")
    private String nombre;
    @Size(min = 10, message = "Los pasos de la receta deben tener al menos 10 caracteres")
    private String pasos;
    @Size(min = 1, message = "La receta debe tener al menos un ingrediente")
    private List<IngredienteRecetaDTO> ingredientesReceta;
}
