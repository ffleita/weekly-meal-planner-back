package ffleitas.back.mappers.mapstructs;

import ffleitas.back.domain.entities.PlanSemanal;
import ffleitas.back.dtos.planes.PlanSemanalDTO;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface PlanSemanalMapper {
    PlanSemanalDTO toDto(PlanSemanal planSemanal);
    List<PlanSemanalDTO> toDTOList(List<PlanSemanal> planSemanales);
}
