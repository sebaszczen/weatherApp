package sebaszczen.servicesFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sebaszczen.dto.UserDto;
import sebaszczen.model.userModel.User;
import sebaszczen.services.userService.UserService;

@Service
public class UserFacadeImpl implements UserFacade {

    private UserService userService;

    @Autowired
    public UserFacadeImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User registerNewUserAccount(UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @Override
    public boolean emailAlreadyInUse(UserDto userDto) {
        return userService.emailAlreadyInUse(userDto);
    }
}
