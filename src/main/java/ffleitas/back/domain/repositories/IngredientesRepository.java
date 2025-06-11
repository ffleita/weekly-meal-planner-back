package ffleitas.back.domain.repositories;

import ffleitas.back.domain.entities.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IngredientesRepository  extends JpaRepository<Ingrediente, Long> {

    @Query("SELECT COUNT(i) > 0 FROM Ingrediente i WHERE LOWER(i.nombre) = LOWER(:nombre)")
    boolean existsByNombreIgnoreCase(@Param("nombre") String nombre);

    @Query("SELECT COUNT(ir) > 0 FROM Ingrediente i " +
            "JOIN IngredienteReceta ir ON i = ir.ingrediente " +
            "WHERE i.id = :ingrediente " +
            "AND ir.id != NULL")
    boolean existsRecetaWithIngrediente(@Param("ingrediente") Integer ingrediente);

    @Query("SELECT i FROM Ingrediente i WHERE i.id = :id AND i.borradoLogico = false")
    Optional<Ingrediente> findByIdNotDeleted(@Param("id") Long id);

    @Query("SELECT i FROM Ingrediente i WHERE i.borradoLogico = false")
    List<Ingrediente> findAllNotDeleted();
}
