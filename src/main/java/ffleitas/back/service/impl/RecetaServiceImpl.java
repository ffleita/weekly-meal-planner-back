package ffleitas.back.service.impl;

import ffleitas.back.domain.entities.Ingrediente;
import ffleitas.back.domain.entities.IngredienteReceta;
import ffleitas.back.domain.entities.Medida;
import ffleitas.back.domain.entities.Receta;
import ffleitas.back.domain.repositories.IngredienteRecetaRepository;
import ffleitas.back.domain.repositories.IngredientesRepository;
import ffleitas.back.domain.repositories.MedidaRepository;
import ffleitas.back.domain.repositories.RecetasRepository;
import ffleitas.back.dtos.ingredientereceta.IngredienteRecetaDTO;
import ffleitas.back.dtos.recetas.CrearRecetaRequest;
import ffleitas.back.dtos.recetas.RecetaDTO;
import ffleitas.back.dtos.recetas.RecetaDetailDTO;
import ffleitas.back.exceptions.ElementoInexistenteException;
import ffleitas.back.exceptions.ErrorAlCrearObjetoException;
import ffleitas.back.mappers.IngredienteRecetaMapper;
import ffleitas.back.mappers.RecetaMapper;
import ffleitas.back.service.RecetaService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Getter
public class RecetaServiceImpl implements RecetaService
{

	private final RecetasRepository recetasRepository;

	private final IngredienteRecetaRepository ingredienteRecetaRepository;

	private final IngredientesRepository ingredientesRepository;

	private final MedidaRepository medidaRepository;

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

	@Override
	@Transactional
	public RecetaDTO crearReceta(CrearRecetaRequest request) {
		Receta receta = getRecetasRepository().obtenerRecetaByNombre(request.getNombre());
		if (receta != null) {
			throw new ErrorAlCrearObjetoException("Ya existe una receta con el nombre: " + request.getNombre());
		}
		if (request.getIngredientesReceta() == null || request.getIngredientesReceta().isEmpty()) {
			throw new ErrorAlCrearObjetoException("La receta debe contener al menos un ingrediente");
		}
		Receta rec = new Receta(request.getNombre(), request.getPasos());
		final var nuevaReceta = getRecetasRepository().save(rec);
		request.getIngredientesReceta().forEach(ingredienteReceta -> crearIngredienteReceta(ingredienteReceta, nuevaReceta));
		return RecetaMapper.toRecetaDTO(nuevaReceta);
	}

	@Override
	public void eliminarReceta(int idReceta) {
		Receta receta = getRecetasRepository().obtenerRecetaNotEliminated(idReceta);
		if (receta != null) {
			receta.setBorradoLogico(true);
			getRecetasRepository().save(receta);
		}
	}

	@Override
	public RecetaDTO actualizarReceta(int idReceta, CrearRecetaRequest request) {
		Receta receta = getRecetasRepository().obtenerRecetaPorId((long) idReceta).orElseThrow(() -> new ElementoInexistenteException("No se encontro la receta que intenta actualizar"));
		if (!receta.getNombre().equalsIgnoreCase(request.getNombre())) {
			receta.setNombre(request.getNombre());
		}
		if (!receta.getPasos().equals(request.getPasos())) {
			receta.setPasos(request.getPasos());
		}
		getRecetasRepository().save(receta);
		List<IngredienteReceta> ingredientesABorrar = getIngredienteRecetaRepository().findIngredienteRecetaByReceta(receta);
		getIngredienteRecetaRepository().deleteAll(ingredientesABorrar);
		request.getIngredientesReceta().forEach(ingredienteReceta -> crearIngredienteReceta(ingredienteReceta, receta));
		return RecetaMapper.toRecetaDTO(receta);
	}

	private void crearIngredienteReceta(IngredienteRecetaDTO ingredienteReceta, Receta receta) {
		final Ingrediente ingrediente = getIngredientesRepository().findByIdNotDeleted((long) ingredienteReceta.getIngrediente()).orElseThrow(() -> new ElementoInexistenteException("No se encontro el ingrediente con id: " + ingredienteReceta.getIngrediente()));
		if (ingrediente == null) {
			throw new ElementoInexistenteException("No se encontro el ingrediente con id: " + ingredienteReceta.getIngrediente());
		}
		final Medida medida = getMedidaRepository().findById(ingredienteReceta.getMedida()).orElseThrow(() -> new ElementoInexistenteException("No se encontro la medida con id: " + ingredienteReceta.getMedida()));
		if (medida == null) {
			throw new ElementoInexistenteException("No se encontro la medida con id: " + ingredienteReceta.getMedida());
		}
		IngredienteReceta nuevoIngredienteReceta = new IngredienteReceta();
		nuevoIngredienteReceta.setReceta(receta);
		nuevoIngredienteReceta.setIngrediente(ingrediente);
		nuevoIngredienteReceta.setMedida(medida);
		nuevoIngredienteReceta.setCantidad(ingredienteReceta.getCantidad());
		getIngredienteRecetaRepository().save(nuevoIngredienteReceta);
	}
}
