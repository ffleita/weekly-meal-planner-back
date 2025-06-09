package ffleitas.back.domain.repositories;

import ffleitas.back.domain.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TokenRepository extends JpaRepository<Token, Long>
{
	List<Token> findAllValidIsFalseOrRevokedIsFalseByUserId(Long userId);

	@Query("SELECT t FROM Token t WHERE t.token = :jwtToken")
	Token findTokenWithJwttoken(@Param("jwtToken") String jwtToken);
}
