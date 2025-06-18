package ffleitas.back.domain.repositories;

import ffleitas.back.domain.entities.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface RecetasRepository extends JpaRepository<Receta, Long>
{
	@Query("SELECT r FROM Receta r where r.borradoLogico = false")
	List<Receta> findAllNotEliminated();
}
