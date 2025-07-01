package ffleitas.back.dtos.planes;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class PlanSemanalDetalleDTO {
    private Long id;
    private String descripcion;
    private List<PlanDiaDTO> dias;
}
