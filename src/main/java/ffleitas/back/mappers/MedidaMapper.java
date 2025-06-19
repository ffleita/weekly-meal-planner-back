package ffleitas.back.mappers;

import ffleitas.back.domain.entities.Medida;
import ffleitas.back.dtos.medidas.MedidaDTO;

import java.util.List;
import java.util.stream.Collectors;


public class MedidaMapper
{
	public static MedidaDTO toMedidaDTO(Medida source) {
		return MedidaDTO.builder().id(source.getId()).nombre(source.getDescripcion()).build();
	}

	public static List<MedidaDTO> toMedidaDTOList(List<Medida> source)
	{
		return source.stream().map(MedidaMapper::toMedidaDTO).collect(Collectors.toList());
	}
}
