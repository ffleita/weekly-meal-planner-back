package ffleitas.back.dtos.recetas;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class RecetaDTO
{
	private Long id;
	private String nombre;
}
