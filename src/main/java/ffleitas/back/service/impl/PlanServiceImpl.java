package ffleitas.back.service.impl;

import ffleitas.back.domain.entities.DiaSemana;
import ffleitas.back.domain.entities.PlanDia;
import ffleitas.back.domain.entities.PlanSemanal;
import ffleitas.back.domain.repositories.PlanDiaRepository;
import ffleitas.back.domain.repositories.PlanRepository;
import ffleitas.back.domain.repositories.RecetasRepository;
import ffleitas.back.dtos.planes.PlanDiaDTO;
import ffleitas.back.dtos.planes.PlanSemanalDTO;
import ffleitas.back.dtos.planes.PlanSemanalDetalleDTO;
import ffleitas.back.dtos.planes.PlanSemanalRequest;
import ffleitas.back.exceptions.ElementoInexistenteException;
import ffleitas.back.mappers.mapstructs.PlanSemanalDetalleMapper;
import ffleitas.back.mappers.mapstructs.PlanSemanalMapper;
import ffleitas.back.service.PlanService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Getter
@RequiredArgsConstructor
@Log4j2
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;
    private final RecetasRepository recetasRepository;
    private final PlanDiaRepository planDiaRepository;
    private final PlanSemanalMapper planSemanalMapper;
    private final PlanSemanalDetalleMapper planSemanalDetalleMapper;

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
        final PlanSemanal plan = getPlanRepository().buscarPlanSemanalPorIdYNoBorradoLogico(id.intValue());
        if (plan == null) {
            throw new ElementoInexistenteException("No existe un plan semanal con el ID proporcionado");
        }
        final PlanSemanalDetalleDTO planDto = getPlanSemanalDetalleMapper().toDto(plan);
        log.info(planDto.toString());
        return planDto;
    }

    @Transactional
    @Override
    public PlanSemanalDTO crearPlan(PlanSemanalRequest planSemanalRequest) {
        PlanSemanal plan = new PlanSemanal();
        plan.setDescripcion(planSemanalRequest.getDescripcion());
        final PlanSemanal nuevoPlanFinal = getPlanRepository().save(plan);
        planSemanalRequest.getDias().forEach(planDiaDTO -> {
            createPlanDiaAndAssignToPlan(planDiaDTO, nuevoPlanFinal);
        });
        return getPlanSemanalMapper().toDto(nuevoPlanFinal);
    }

    public void createPlanDiaAndAssignToPlan(PlanDiaDTO planDiaDTO, PlanSemanal planSemanal) {
        PlanDia planDia = new PlanDia();
        planDia.setPlanSemanal(planSemanal);
        planDia.setDiaSemana(getDiaSemanaDesdeString(planDiaDTO.getDiaSemana()));
        planDia.setCenaReceta(getRecetasRepository().obtenerRecetaNotEliminated(planDiaDTO.getCenaReceta().getId().intValue()));
        planDia.setAlmuerzoReceta(getRecetasRepository().obtenerRecetaNotEliminated(planDiaDTO.getAlmuerzoReceta().getId().intValue()));
        getPlanDiaRepository().save(planDia);
    }

    public static DiaSemana getDiaSemanaDesdeString(String dia) {
        return switch (dia.toLowerCase()) {
            case "lunes" -> DiaSemana.LUNES;
            case "martes" -> DiaSemana.MARTES;
            case "miercoles" -> DiaSemana.MIERCOLES;
            case "jueves" -> DiaSemana.JUEVES;
            case "viernes" -> DiaSemana.VIERNES;
            default -> throw new IllegalArgumentException("Día de la semana no válido: " + dia);
        };
    }

    @Transactional
    @Override
    public void eliminarPlanPorId(Integer id) {
        PlanSemanal planSemanal = getPlanRepository().buscarPlanSemanalPorIdYNoBorradoLogico(id);
        if (planSemanal != null) {
            planSemanal.setBorradoLogico(true);
            getPlanRepository().save(planSemanal);
        }
    }

}
