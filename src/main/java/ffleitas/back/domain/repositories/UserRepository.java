package ffleitas.back.domain.repositories;

import ffleitas.back.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long>
{

	Optional<User> findByUsername(String username);

	@Query("SELECT u FROM User u ORDER BY u.username ASC")
	List<User> findAllUsersOrderedByUsername();
}
