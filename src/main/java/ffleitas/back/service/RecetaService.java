package ffleitas.back.service;

import ffleitas.back.dtos.recetas.RecetaDTO;

import java.util.List;


public interface RecetaService {

	List<RecetaDTO> listarRecetas();
}
