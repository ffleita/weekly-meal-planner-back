package ffleitas.back.domain.repositories;

import ffleitas.back.domain.entities.PlanSemanal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlanRepository extends JpaRepository<PlanSemanal, Long> {

    @Query("SELECT ps FROM PlanSemanal ps WHERE ps.borradoLogico = false")
    List<PlanSemanal> listarPlanesSemanalesNoBorradoLogico();


    @Query("SELECT ps FROM PlanSemanal ps WHERE ps.id = :id AND ps.borradoLogico = false")
    PlanSemanal buscarPlanSemanalPorIdYNoBorradoLogico(Integer id);
}
