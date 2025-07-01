package ffleitas.back.controllers;

import ffleitas.back.dtos.planes.PlanSemanalDTO;
import ffleitas.back.dtos.planes.PlanSemanalDetalleDTO;
import ffleitas.back.dtos.planes.PlanSemanalRequest;
import ffleitas.back.service.PlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/planes")
@RequiredArgsConstructor
@Getter
@Tag(name = "Planes", description = "Controlador para gestionar planes semanales")
public class PlanesController {

    private final PlanService planService;

    @Operation(summary = "Listar planes semanales")
    @GetMapping()
    public ResponseEntity<List<PlanSemanalDTO>> listarPlanes() {
        return ResponseEntity.ok(getPlanService().listarPlanes());
    }

    @Operation(summary = "Borrado logico de plan semanal por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPlanPorId(@PathVariable Integer id) {
        getPlanService().eliminarPlanPorId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanSemanalDetalleDTO> obtenerDetalleDePlanPorId(@PathVariable Long id) {
        return ResponseEntity.ok(getPlanService().obtenerDetalleDePlanPorId(id));
    }

    @PostMapping()
    public ResponseEntity<PlanSemanalDTO> crearPlanSemanal(@Valid @RequestBody PlanSemanalRequest request) {
        PlanSemanalDTO nuevoPlan = getPlanService().crearPlan(request);
        return ResponseEntity.created(URI.create("/planes/" + nuevoPlan.getId())).body(nuevoPlan);
    }
}
