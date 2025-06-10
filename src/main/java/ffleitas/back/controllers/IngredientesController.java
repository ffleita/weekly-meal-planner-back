package ffleitas.back.controllers;


import ffleitas.back.dtos.ingredientes.IngredientesResponse;
import ffleitas.back.service.IngredienteService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("ingredientes")
@RequiredArgsConstructor
public class IngredientesController {

    private static final Logger log = LoggerFactory.getLogger(IngredientesController.class);

    @Resource
    private final IngredienteService ingredientesService;



    @GetMapping
    public ResponseEntity<IngredientesResponse> getAllIngredients() {
        try {
            return new ResponseEntity<>(getIngredientesService().getAllIngredients(), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(new IngredientesResponse(HttpStatus.NOT_FOUND.toString(), List.of(), e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    public IngredienteService getIngredientesService() {
        return ingredientesService;
    }
}
