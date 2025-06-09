package ffleitas.back.service.impl;

import ffleitas.back.mappers.UserMapper;
import ffleitas.back.domain.entities.User;
import ffleitas.back.domain.repositories.UserRepository;
import ffleitas.back.dtos.users.UserDTO;
import ffleitas.back.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Getter
@Setter
public class UserServiceImpl implements UserService
{
	private final UserRepository userRepository;
	private UserMapper userMapper;
	private static final List<String> AVAILABLE_ROLES = List.of("ADMIN", "USER");

	@Override
	public List<UserDTO> getAllUsers()
	{
		final List<User> users = userRepository.findAllUsersOrderedByUsername();
		if (!users.isEmpty()) {
			return UserMapper.mapAllToUserDTO(users);
		}
		return List.of();
	}

	@Override
	public List<String> getAvailableRolesForUsers()
	{
		return AVAILABLE_ROLES;
	}
}
