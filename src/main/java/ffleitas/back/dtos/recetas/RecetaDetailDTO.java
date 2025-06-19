package ffleitas.back.dtos.recetas;

import ffleitas.back.dtos.ingredientereceta.IngredienteRecetaDTO;
import lombok.*;

import java.util.List;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecetaDetailDTO
{
	String nombre;
	List<IngredienteRecetaDTO> ingredientesReceta;
	String pasos;
}
