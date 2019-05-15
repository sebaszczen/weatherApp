package sebaszczen.services.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sebaszczen.dto.UserDto;
import sebaszczen.model.userModel.User;
import sebaszczen.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveUser(UserDto userDto) {
        String encode = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encode);
        return userRepository.save(new User(userDto));
    }

    @Override
    public boolean emailAlreadyInUse(UserDto userDto) {
        return userRepository.existsByEmail(userDto.getEmail());
    }
}
