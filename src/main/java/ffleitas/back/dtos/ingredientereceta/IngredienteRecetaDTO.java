package ffleitas.back.dtos.ingredientereceta;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class IngredienteRecetaDTO
{
	private int ingrediente;
	private int cantidad;
	private Long medida;
}
