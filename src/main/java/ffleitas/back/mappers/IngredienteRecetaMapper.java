package ffleitas.back.mappers;

import ffleitas.back.domain.entities.IngredienteReceta;
import ffleitas.back.dtos.ingredientereceta.IngredienteRecetaDTO;

import java.util.List;
import java.util.stream.Collectors;


public class IngredienteRecetaMapper
{
	public static IngredienteRecetaDTO toIngredienteRecetaDTO(IngredienteReceta source)
	{
		return IngredienteRecetaDTO.builder().ingrediente(source.getIngrediente().getId()).cantidad(source.getCantidad())
				.medida(source.getMedida().getId()).build();
	}

	public static List<IngredienteRecetaDTO> toIngredienteRecetaDTOList(List<IngredienteReceta> source)
	{
		return source.stream().map(IngredienteRecetaMapper::toIngredienteRecetaDTO).collect(Collectors.toList());
	}
}
