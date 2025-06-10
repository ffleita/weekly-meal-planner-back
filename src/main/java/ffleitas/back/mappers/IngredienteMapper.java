package ffleitas.back.mappers;

import ffleitas.back.domain.entities.Ingrediente;
import ffleitas.back.dtos.ingredientes.IngredienteDTO;

public class IngredienteMapper {

    public static IngredienteDTO toIngredienteDTO(Ingrediente source) {
        return IngredienteDTO.builder()
                .id(source.getId())
                .nombre(source.getNombre())
                .build();
    }
}
