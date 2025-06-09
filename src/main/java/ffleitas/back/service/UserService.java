package ffleitas.back.service;

import ffleitas.back.domain.entities.User;
import ffleitas.back.dtos.users.UserDTO;

import java.util.List;


public interface UserService
{
	List<UserDTO> getAllUsers();

	List<String> getAvailableRolesForUsers();
}
