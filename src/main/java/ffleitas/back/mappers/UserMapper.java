package ffleitas.back.mappers;

import ffleitas.back.domain.entities.User;
import ffleitas.back.dtos.users.UserDTO;

import java.util.List;
import java.util.stream.Collectors;


public class UserMapper
{
	public static UserDTO toUserDTO(User user)
	{
		return UserDTO.builder()
				.id(user.getId())
				.username(user.getUsername())
				.email(user.getEmail())
				.role(user.getRole())
				.build();
	}

	public static List<UserDTO> mapAllToUserDTO(List<User> users) {
		return users.stream().map(UserMapper::toUserDTO).collect(Collectors.toList());
	}
}
