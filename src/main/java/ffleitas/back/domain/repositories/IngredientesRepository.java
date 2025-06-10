package ffleitas.back.domain.repositories;

import ffleitas.back.domain.entities.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IngredientesRepository  extends JpaRepository<Ingrediente, Long> {

    @Query("SELECT COUNT(i) > 0 FROM Ingrediente i WHERE LOWER(i.nombre) = LOWER(:nombre)")
    boolean existsByNombreIgnoreCase(@Param("nombre") String nombre);
}
