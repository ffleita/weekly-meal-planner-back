package ffleitas.back.dtos.planes;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@Getter
@ToString
public class PlanSemanalRequest {
    @NotNull(message = "El nombre del plan es obligatorio")
    private String descripcion;
    @Size(min = 5, message = "Debe ingresar al menos 5 días")
    List<PlanDiaDTO> dias;
}
