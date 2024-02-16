package dev.paradise.paradisespringboot.controllers;

import dev.paradise.paradisespringboot.models.User;
import dev.paradise.paradisespringboot.objects.UserDTO;
import dev.paradise.paradisespringboot.requests.CreateUserRequest;
import dev.paradise.paradisespringboot.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Controller Đăng nhâp
    @GetMapping("/me")
    public String loggedInUser(Principal principal){
        return principal.getName()+" : Login successfully";
    }
    //Controller đăng ký tài khoản
    @PostMapping("/register")
    public ResponseEntity<UserDTO> signUp(@RequestBody CreateUserRequest request){
        User user = userService.createUser(request);
        UserDTO responseUser = new UserDTO(user.getName(), user.getUsername(), user.getRoles());
        return new ResponseEntity<>(responseUser, HttpStatus.CREATED);
    }
}
