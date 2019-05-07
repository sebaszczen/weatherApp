package sebaszczen.services.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sebaszczen.dto.UserDto;
import sebaszczen.model.userModel.User;
import sebaszczen.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(UserDto userDto) {
        return userRepository.save(new User(userDto));
    }

    @Override
    public boolean emailAlreadyInUse(UserDto userDto) {
        return userRepository.existsByEmail(userDto.getEmail());
    }
}
