package sebaszczen.servicesFacade;

import sebaszczen.dto.UserDto;
import sebaszczen.model.userModel.User;

import javax.validation.Valid;

public interface UserFacade {

    public User registerNewUserAccount(UserDto userDto);

    boolean emailAlreadyInUse(UserDto userDto);
}
