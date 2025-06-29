package ffleitas.back.domain.repositories;

import ffleitas.back.domain.entities.IngredienteReceta;
import ffleitas.back.domain.entities.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface IngredienteRecetaRepository extends JpaRepository<IngredienteReceta, Long>
{

	@Query("SELECT ir FROM IngredienteReceta ir" +
			" INNER JOIN Ingrediente i on i = ir.ingrediente" +
			" WHERE ir.receta = :receta and i.borradoLogico = false")
	List<IngredienteReceta> findIngredienteRecetaByReceta(Receta receta);
}
