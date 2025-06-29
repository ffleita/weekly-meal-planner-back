package ffleitas.back.controllers;

import ffleitas.back.dtos.recetas.CrearRecetaRequest;
import ffleitas.back.dtos.recetas.RecetaDTO;
import ffleitas.back.dtos.recetas.RecetaDetailDTO;
import ffleitas.back.service.RecetaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/recetas")
@RequiredArgsConstructor
@Getter
@Tag(name = "Recetas", description = "Operaciones CRUD sobre recetas")
public class RecetasController
{

	private final RecetaService recetaService;

	@Operation(summary = "Obtener todas las recetas disponibles")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "No se encontraron recetas disponibles"),
			@ApiResponse(responseCode = "400", description = "Error general") })
	@GetMapping()
	public ResponseEntity<List<RecetaDTO>> listarRecetas()
	{
		return ResponseEntity.ok(getRecetaService().listarRecetas());
	}

	@Operation(summary = "Obtener detalle de receta")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "No se encontraron recetas disponibles"),
			@ApiResponse(responseCode = "400", description = "Error general") })
	@GetMapping("{idReceta}")
	public ResponseEntity<RecetaDetailDTO> getDetalleReceta(@PathVariable int idReceta)
	{
		return ResponseEntity.ok(getRecetaService().obtenerDetalleRecetaPorId(idReceta));
	}

	@Operation(summary = "Crear una nueva receta")
	@PostMapping()
	public ResponseEntity<RecetaDTO> crearReceta(@Valid @RequestBody CrearRecetaRequest request) {
		RecetaDTO recetaCreada = getRecetaService().crearReceta(request);
		return ResponseEntity.created(URI.create("/recetas/" + recetaCreada.getId()))
				.body(recetaCreada);
	}

	@Operation(summary = "Eliminar una receta por ID")
	@DeleteMapping("{id}")
	public ResponseEntity<RecetaDTO> eliminarReceta(@PathVariable int id) {
		getRecetaService().eliminarReceta(id);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Actualizar una receta existente")
	@PutMapping("{id}")
	public ResponseEntity<RecetaDTO> actualizarReceta(@PathVariable int id, @Valid @RequestBody CrearRecetaRequest request) {
		RecetaDTO recetaActualizada = getRecetaService().actualizarReceta(id, request);
		return ResponseEntity.ok(recetaActualizada);
	}
}
