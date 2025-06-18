package ffleitas.back.mappers;

import ffleitas.back.domain.entities.Ingrediente;
import ffleitas.back.dtos.ingredientes.IngredienteDTO;

import java.util.List;
import java.util.stream.Collectors;


public class IngredienteMapper {

    public static IngredienteDTO toIngredienteDTO(Ingrediente source) {
        return IngredienteDTO.builder()
                .id(source.getId())
                .nombre(source.getNombre())
                .build();
    }

    public static List<IngredienteDTO> allToIngredienteDTO(List<Ingrediente> source)
    {
        return source.stream().map(IngredienteMapper::toIngredienteDTO).collect(Collectors.toList());
    }
}
