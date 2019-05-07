package sebaszczen.services.userService;


import sebaszczen.dto.UserDto;
import sebaszczen.model.userModel.User;

public interface UserService {

    public User saveUser(UserDto userDto);

    boolean emailAlreadyInUse(UserDto userDto);
}
