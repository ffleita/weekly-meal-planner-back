package ffleitas.back.dtos.users;

import ffleitas.back.dtos.roles.RoleDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class UserDTO
{
	private Long id;
	private String username;
	private String email;
	private String role;
}
