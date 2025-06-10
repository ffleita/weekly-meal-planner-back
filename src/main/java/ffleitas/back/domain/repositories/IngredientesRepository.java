package ffleitas.back.domain.repositories;

import ffleitas.back.domain.entities.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientesRepository  extends JpaRepository<Ingrediente, Long> {


}
