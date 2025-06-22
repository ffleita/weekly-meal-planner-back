package ffleitas.back.service;

import ffleitas.back.dtos.ingredientes.CrearIngredienteRequest;
import ffleitas.back.dtos.ingredientes.IngredienteCreadoResponse;
import ffleitas.back.dtos.ingredientes.IngredienteDTO;
import ffleitas.back.dtos.ingredientes.IngredientesResponse;

public interface IngredienteService {

    IngredientesResponse getAllIngredients();

    IngredienteCreadoResponse createIngredient(CrearIngredienteRequest request);

    void deleteIngredient(Integer id);

    IngredienteDTO findIngredientById(Integer id);

    IngredienteDTO updateIngrediente(IngredienteDTO ingredienteDTO);
}
