package ffleitas.back.service.impl;

import ffleitas.back.domain.repositories.IngredienteRecetaRepository;
import ffleitas.back.domain.repositories.RecetasRepository;
import ffleitas.back.dtos.recetas.RecetaDTO;
import ffleitas.back.dtos.recetas.RecetaDetailDTO;
import ffleitas.back.exceptions.ElementoInexistenteException;
import ffleitas.back.mappers.IngredienteRecetaMapper;
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

	private final IngredienteRecetaRepository ingredienteRecetaRepository;

	@Override
	public List<RecetaDTO> listarRecetas()
	{
		final var listadoRecetas = getRecetasRepository().findAllNotEliminated();
		if (listadoRecetas.isEmpty())
		{
			throw new ElementoInexistenteException("No se encontraron recetas activas");
		}
		return RecetaMapper.allToRecetaDTO(listadoRecetas);
	}

	@Override
	public RecetaDetailDTO obtenerDetalleRecetaPorId(int id)
	{
		final var receta = getRecetasRepository().findById((long) id).orElse(null);
		if (receta == null)
		{
			throw new ElementoInexistenteException("Receta no encontrada");
		}
		final var listadoIngredientesReceta = getIngredienteRecetaRepository().findIngredienteRecetaByReceta(receta);
		if (listadoIngredientesReceta.isEmpty())
		{
			throw new ElementoInexistenteException("La receta no tiene ingredientes asociados");
		}
		final var listadoIngRecDTO = IngredienteRecetaMapper.toIngredienteRecetaDTOList(listadoIngredientesReceta);
		return RecetaDetailDTO.builder().nombre(receta.getNombre()).ingredientesReceta(listadoIngRecDTO).pasos(receta.getPasos())
				.build();
	}
}
