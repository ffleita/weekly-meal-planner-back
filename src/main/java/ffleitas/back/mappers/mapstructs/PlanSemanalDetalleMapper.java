package ffleitas.back.mappers.mapstructs;

import ffleitas.back.domain.entities.PlanDia;
import ffleitas.back.domain.entities.PlanSemanal;
import ffleitas.back.domain.entities.Receta;
import ffleitas.back.dtos.planes.PlanDiaDTO;
import ffleitas.back.dtos.planes.PlanSemanalDetalleDTO;
import ffleitas.back.dtos.recetas.RecetaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlanSemanalDetalleMapper {
    @Mapping(target = "dias", source = "planDias")
    PlanSemanalDetalleDTO toDto(PlanSemanal entity);

    PlanDiaDTO toDto(PlanDia entity);
    RecetaDTO toDto(Receta entity);
}
