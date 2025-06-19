package ffleitas.back.dtos.medidas;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MedidaDTO
{
	private Long id;
	private String nombre;
}
