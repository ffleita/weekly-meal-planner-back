package ffleitas.back.service;

import ffleitas.back.dtos.planes.PlanSemanalDTO;
import ffleitas.back.dtos.planes.PlanSemanalDetalleDTO;
import ffleitas.back.dtos.planes.PlanSemanalRequest;

import java.util.List;

public interface PlanService {

    List<PlanSemanalDTO> listarPlanes();
    PlanSemanalDetalleDTO obtenerDetalleDePlanPorId(Long id);
    PlanSemanalDTO crearPlan(PlanSemanalRequest planSemanalRequest);
    void eliminarPlanPorId(Long id);
}
