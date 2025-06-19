package ffleitas.back.domain.repositories;

import ffleitas.back.domain.entities.IngredienteReceta;
import ffleitas.back.domain.entities.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface IngredienteRecetaRepository extends JpaRepository<IngredienteReceta, Long>
{


	@Query("SELECT ir FROM IngredienteReceta ir WHERE ir.receta = :receta")
	List<IngredienteReceta> findIngredienteRecetaByReceta(Receta receta);
}
