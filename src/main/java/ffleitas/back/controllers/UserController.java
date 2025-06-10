package ffleitas.back.controllers;

import ffleitas.back.domain.entities.User;
import ffleitas.back.domain.repositories.UserRepository;
import ffleitas.back.dtos.users.UserDTO;
import ffleitas.back.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Getter
@Setter
public class UserController
{
	private final UserService userService;
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@GetMapping()
	public ResponseEntity<List<UserDTO>> getAllUsers()
	{
		try
		{
			return ResponseEntity.ok(getUserService().getAllUsers());
		}
		catch(Exception e)
		{
			log.error("Ocurrio un error al obtener todos los usuarios." , e);
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/roles")
	public ResponseEntity<List<String>> getAvailableRolesForUsers() {
		try {
			return ResponseEntity.ok(getUserService().getAvailableRolesForUsers());
		} catch (Exception e) {
			log.error("Ocurrio un error al retornar todos los roles disponibles para usuariois.", e);
			return ResponseEntity.internalServerError().build();
		}
	}


}
