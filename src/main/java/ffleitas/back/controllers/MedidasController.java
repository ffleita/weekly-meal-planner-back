package ffleitas.back.controllers;

import ffleitas.back.dtos.medidas.MedidaDTO;
import ffleitas.back.exceptions.ElementoInexistenteException;
import ffleitas.back.service.MedidasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/medidas")
@RequiredArgsConstructor
@Getter
@Tag(name = "Medidas", description = "Listar unidades de medida")
public class MedidasController
{
	private final MedidasService medidasService;

	@Operation(summary = "Listar unidades de medida disponibles")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "400", description = "Error general"),
			@ApiResponse(responseCode = "404", description = "No se hallaron resultados para la busqueda")
	})
	@GetMapping()
	public ResponseEntity<List<MedidaDTO>> listarMedidas()
	{
		try
		{
			return ResponseEntity.ok(medidasService.listarMedidas());
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
}
