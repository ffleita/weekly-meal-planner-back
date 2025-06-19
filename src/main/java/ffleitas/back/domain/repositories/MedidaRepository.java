package ffleitas.back.domain.repositories;

import ffleitas.back.domain.entities.Medida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MedidaRepository extends JpaRepository<Medida, Long>
{

	@Query("SELECT m FROM Medida m WHERE m.borradoLogico = false")
	List<Medida> findAllNotDeteled();
}
