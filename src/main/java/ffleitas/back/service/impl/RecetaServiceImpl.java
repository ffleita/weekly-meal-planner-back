package ffleitas.back.service.impl;

import ffleitas.back.domain.repositories.RecetasRepository;
import ffleitas.back.dtos.recetas.RecetaDTO;
import ffleitas.back.exceptions.ElementoInexistenteException;
import ffleitas.back.mappers.RecetaMapper;
import ffleitas.back.service.RecetaService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Getter
public class RecetaServiceImpl implements RecetaService
{

	private final RecetasRepository recetasRepository;

	@Override
	public List<RecetaDTO> listarRecetas()
	{
		final var listadoRecetas = getRecetasRepository().findAllNotEliminated();
		if (listadoRecetas.isEmpty()) {
			throw new ElementoInexistenteException("No se encontraron recetas activas");
		}
		return RecetaMapper.allToRecetaDTO(listadoRecetas);
	}
}
