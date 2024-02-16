package dev.paradise.paradisespringboot.services;

import dev.paradise.paradisespringboot.models.User;
import dev.paradise.paradisespringboot.repositories.UserRepository;
import dev.paradise.paradisespringboot.requests.CreateUserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public User createUser(CreateUserRequest request){

        User user = new User();
        user.setName(request.getName());
        //Kiểm tra user tồn tại chưa

        user.setUsername(request.getUsername());
        user.setRoles(request.getRoles());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
        return user;
    }
}
