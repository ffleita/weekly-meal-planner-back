package ffleitas.back.mappers;

import ffleitas.back.domain.entities.Receta;
import ffleitas.back.dtos.recetas.RecetaDTO;

import java.util.List;
import java.util.stream.Collectors;


public class RecetaMapper
{

	public static RecetaDTO toRecetaDTO(Receta receta)
	{
		return RecetaDTO.builder().id(receta.getId()).nombre(receta.getNombre()).build();
	}

	public static List<RecetaDTO> allToRecetaDTO(List<Receta> recetas)
	{
		return recetas.stream().map(RecetaMapper::toRecetaDTO).collect(Collectors.toList());
	}
}
