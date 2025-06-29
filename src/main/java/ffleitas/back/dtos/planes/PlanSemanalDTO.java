package ffleitas.back.dtos.planes;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PlanSemanalDTO {
    private Long id;
    private String descripcion;
}
