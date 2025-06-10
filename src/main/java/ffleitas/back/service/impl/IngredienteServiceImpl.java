package ffleitas.back.service.impl;

import ffleitas.back.domain.entities.Ingrediente;
import ffleitas.back.domain.repositories.IngredientesRepository;
import ffleitas.back.dtos.ingredientes.CrearIngredienteRequest;
import ffleitas.back.dtos.ingredientes.IngredienteCreadoResponse;
import ffleitas.back.dtos.ingredientes.IngredientesResponse;
import ffleitas.back.exceptions.ErrorAlCrearObjetoException;
import ffleitas.back.mappers.IngredienteMapper;
import ffleitas.back.service.IngredienteService;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
public class IngredienteServiceImpl implements IngredienteService {

    @Resource
    private final IngredientesRepository ingredientesRepository;

    @Override
    public IngredientesResponse getAllIngredients() {
        return new IngredientesResponse(HttpStatus.OK.toString(), getIngredientesRepository().findAll(), "");
    }

    @Override
    public IngredienteCreadoResponse createIngredient(CrearIngredienteRequest request) {
        if (request.nombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del ingrediente no puede ser vacio");
        }
        if (getIngredientesRepository().existsByNombreIgnoreCase(request.nombre())) {
            throw new IllegalArgumentException(String.format("Ya existe un ingrediente con el nombre: %s", request.nombre()));
        }
        try {
            Ingrediente  ingrediente = Ingrediente.builder()
                    .nombre(request.nombre())
                    .build();
            getIngredientesRepository().save(ingrediente);
            return new IngredienteCreadoResponse(HttpStatus.CREATED.toString(), IngredienteMapper.toIngredienteDTO(ingrediente), "");
        } catch (Exception e) {
            throw new ErrorAlCrearObjetoException(e.getMessage());
        }
    }
}
