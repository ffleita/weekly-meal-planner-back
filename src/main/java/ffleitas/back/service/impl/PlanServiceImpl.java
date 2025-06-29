package ffleitas.back.service.impl;

import ffleitas.back.domain.entities.PlanSemanal;
import ffleitas.back.domain.repositories.PlanRepository;
import ffleitas.back.dtos.planes.PlanSemanalDTO;
import ffleitas.back.dtos.planes.PlanSemanalDetalleDTO;
import ffleitas.back.dtos.planes.PlanSemanalRequest;
import ffleitas.back.exceptions.ElementoInexistenteException;
import ffleitas.back.mappers.mapstructs.PlanSemanalMapper;
import ffleitas.back.service.PlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;
    private final PlanSemanalMapper planSemanalMapper;

    public PlanServiceImpl(PlanRepository planRepository, PlanSemanalMapper planSemanalMapper) {
        this.planRepository = planRepository;
        this.planSemanalMapper = planSemanalMapper;
    }

    @Override
    public List<PlanSemanalDTO> listarPlanes() {
        List<PlanSemanal> planes = getPlanRepository().listarPlanesSemanalesNoBorradoLogico();
        if (planes.isEmpty()) {
            throw new ElementoInexistenteException("No existen planes semanales disponibles");
        }
        return getPlanSemanalMapper().toDTOList(planes);
    }

    @Override
    public PlanSemanalDetalleDTO obtenerDetalleDePlanPorId(Long id) {
        return null;
    }

    @Override
    public PlanSemanalDTO crearPlan(PlanSemanalRequest planSemanalRequest) {
        return null;
    }

    @Override
    public void eliminarPlanPorId(Long id) {

    }

    public PlanRepository getPlanRepository() {
        return planRepository;
    }

    public PlanSemanalMapper getPlanSemanalMapper() {
        return planSemanalMapper;
    }
}
