package ffleitas.back.dtos.ingredientes;

import ffleitas.back.domain.entities.Ingrediente;

import java.util.List;

public record IngredientesResponse(String status, List<IngredienteDTO> ingredientes, String errorMessage) {
}
