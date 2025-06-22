package ffleitas.back.controllers;


import ffleitas.back.dtos.ingredientes.CrearIngredienteRequest;
import ffleitas.back.dtos.ingredientes.IngredienteCreadoResponse;
import ffleitas.back.dtos.ingredientes.IngredienteDTO;
import ffleitas.back.dtos.ingredientes.IngredientesResponse;
import ffleitas.back.exceptions.DependenciasActivasException;
import ffleitas.back.exceptions.ElementoInexistenteException;
import ffleitas.back.service.IngredienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
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
@Getter
@Tag(name = "Ingredientes", description = "Operaciones CRUD para ingredientes")
public class IngredientesController
{
	private static final Logger log = LoggerFactory.getLogger(IngredientesController.class);

	private final IngredienteService ingredientesService;

	@Operation(summary = "Obtener todos los ingredientes")
	@GetMapping()
	public ResponseEntity<IngredientesResponse> getAllIngredients()
	{
		try
		{
			return new ResponseEntity<>(getIngredientesService().getAllIngredients(), HttpStatus.OK);
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return new ResponseEntity<>(new IngredientesResponse(HttpStatus.NOT_FOUND.toString(), List.of(), e.getMessage()),
					HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<IngredienteDTO> findIngredienteById(@PathVariable Integer id)
	{
		try
		{
			return new ResponseEntity<>(getIngredientesService().findIngredientById(id), HttpStatus.OK);
		}
		catch (ElementoInexistenteException e)
		{
			log.error(e.getMessage());
			return ResponseEntity.notFound().build();
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}

	@Operation(summary = "Crear nuevo ingrediente")
	@PostMapping
	public ResponseEntity<IngredienteCreadoResponse> createIngredient(@RequestBody CrearIngredienteRequest request)
	{
		try
		{
			return new ResponseEntity<>(getIngredientesService().createIngredient(request), HttpStatus.CREATED);
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return new ResponseEntity<>(new IngredienteCreadoResponse(HttpStatus.BAD_GATEWAY.toString(), null, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(summary = "Eliminar ingrediente")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Eliminacion exitosa"),
			@ApiResponse(responseCode = "404", description = "Ingrediente no encontrado"),
			@ApiResponse(responseCode = "409", description = "Ingrediente asociado a receta"),
			@ApiResponse(responseCode = "403", description = "Error de autorizacion"),
			@ApiResponse(responseCode = "400", description = "Error generico") })
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteIngredient(@PathVariable Integer id)
	{
		try
		{
			getIngredientesService().deleteIngredient(id);
			return ResponseEntity.noContent().build();
		}
		catch (ElementoInexistenteException e)
		{
			log.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		catch (DependenciasActivasException e)
		{
			log.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}

