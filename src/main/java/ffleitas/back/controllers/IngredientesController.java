package ffleitas.back.controllers;


import ffleitas.back.dtos.ingredientes.CrearIngredienteRequest;
import ffleitas.back.dtos.ingredientes.IngredienteCreadoResponse;
import ffleitas.back.dtos.ingredientes.IngredienteDTO;
import ffleitas.back.dtos.ingredientes.IngredientesResponse;
import ffleitas.back.service.IngredienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping("ingredientes")
@RequiredArgsConstructor
@Getter
@Tag(name = "Ingredientes", description = "Operaciones CRUD para ingredientes")
public class IngredientesController {
    private static final Logger log = LoggerFactory.getLogger(IngredientesController.class);

    private final IngredienteService ingredientesService;

    @Operation(summary = "Obtener todos los ingredientes")
    @GetMapping()
    public ResponseEntity<IngredientesResponse> getAllIngredients() {
        return ResponseEntity.ok(getIngredientesService().getAllIngredients());
    }

    @Operation(summary = "Obtener ingrediente por id")
    @GetMapping("{id}")
    public ResponseEntity<IngredienteDTO> findIngredienteById(@PathVariable Integer id) {
        return ResponseEntity.ok(getIngredientesService().findIngredientById(id));
    }

    @Operation(summary = "Crear nuevo ingrediente")
    @PostMapping
    public ResponseEntity<IngredienteCreadoResponse> createIngredient(@RequestBody CrearIngredienteRequest request) {
//      Creo el nuevo ingrediente y retorno una respuesta con la cabecera Location con la URI del nuevo recurso, ademas retorno el objeto creado.
        final var nuevoingrediente = getIngredientesService().createIngredient(request);
        return ResponseEntity.created(URI.create("/ingredientes/" + nuevoingrediente.ingredienteDTO().getId())).body(nuevoingrediente);
    }

    @Operation(summary = "Actualizar ingrediente")
    @PutMapping("")
    public ResponseEntity<IngredienteDTO> updateIngrediente(@RequestBody IngredienteDTO ingredienteDTO) {
        return ResponseEntity.ok(getIngredientesService().updateIngrediente(ingredienteDTO));
    }

    @Operation(summary = "Eliminar ingrediente")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Eliminacion exitosa"),
            @ApiResponse(responseCode = "404", description = "Ingrediente no encontrado"),
            @ApiResponse(responseCode = "409", description = "Ingrediente asociado a receta"),
            @ApiResponse(responseCode = "403", description = "Error de autorizacion"),
            @ApiResponse(responseCode = "400", description = "Error generico")})
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteIngredient(@PathVariable Integer id) {
        getIngredientesService().deleteIngredient(id);
        return ResponseEntity.noContent().build();
    }

}

