package ffleitas.back.controllers;

import ffleitas.back.dtos.planes.PlanSemanalDTO;
import ffleitas.back.service.PlanService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/planes")
@RequiredArgsConstructor
@Getter
public class PlanesController {

    private final PlanService planService;

    @GetMapping()
    public ResponseEntity<List<PlanSemanalDTO>> listarPlanes() {
        return ResponseEntity.ok(getPlanService().listarPlanes());
    }
}
