package ffleitas.back.controllers;

import ffleitas.back.dtos.recetas.RecetaDTO;
import ffleitas.back.dtos.recetas.RecetaDetailDTO;
import ffleitas.back.exceptions.ElementoInexistenteException;
import ffleitas.back.service.RecetaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		try
		{
			return ResponseEntity.ok(getRecetaService().listarRecetas());
		}
		catch (ElementoInexistenteException e)
		{
			return ResponseEntity.notFound().build();
		}
		catch (Exception e)
		{
			return ResponseEntity.badRequest().build();
		}
	}

	@Operation(summary = "Obtener detalle de receta")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "No se encontraron recetas disponibles"),
			@ApiResponse(responseCode = "400", description = "Error general") })
	@GetMapping("{idReceta}")
	public ResponseEntity<RecetaDetailDTO> getDetalleReceta(@PathVariable int idReceta)
	{
		try
		{
			return ResponseEntity.ok(getRecetaService().obtenerDetalleRecetaPorId(idReceta));
		}
		catch (ElementoInexistenteException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
