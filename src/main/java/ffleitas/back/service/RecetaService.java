package ffleitas.back.service;

import ffleitas.back.dtos.recetas.CrearRecetaRequest;
import ffleitas.back.dtos.recetas.RecetaDTO;
import ffleitas.back.dtos.recetas.RecetaDetailDTO;

import java.util.List;


public interface RecetaService {

	List<RecetaDTO> listarRecetas();

	RecetaDetailDTO obtenerDetalleRecetaPorId(int id);

	RecetaDTO crearReceta(final CrearRecetaRequest request);
}
