package ffleitas.back.service.impl;

import ffleitas.back.domain.repositories.IngredientesRepository;
import ffleitas.back.dtos.ingredientes.IngredientesResponse;
import ffleitas.back.service.IngredienteService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngredienteServiceImpl implements IngredienteService {

    @Resource
    private final IngredientesRepository ingredientesRepository;

    @Override
    public IngredientesResponse getAllIngredients() {

        return new IngredientesResponse(HttpStatus.OK.toString(), getIngredientesRepository().findAll(), "");
    }

    public IngredientesRepository getIngredientesRepository() {
        return ingredientesRepository;
    }
}
