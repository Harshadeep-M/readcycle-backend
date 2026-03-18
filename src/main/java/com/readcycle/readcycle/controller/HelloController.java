package com.readcycle.readcycle.controller;
import com.readcycle.readcycle.entity.User;
import com.readcycle.readcycle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

import com.readcycle.readcycle.dto.UserDTO;
import com.readcycle.readcycle.dto.LoginRequest;
import com.readcycle.readcycle.dto.ApiResponse;

@RestController
public class  HelloController {
@Autowired
private UserService userService;
@GetMapping("/users")
public List<UserDTO> getAllUsers(
        @RequestParam int page,
        @RequestParam int size
){
   return userService.getAllUsers(page, size);
}
@PostMapping("/users")
    public UserDTO createUser(@Valid @RequestBody User user){
    User savedUser = userService.createUser(user);
    return new UserDTO(
            savedUser.getId(),
            savedUser.getName(),
            savedUser.getEmail()

    );
}
@GetMapping("/users/{id}")
    public UserDTO getUserById(@PathVariable Long id){
    User user = userService.getUserById(id);
    return new UserDTO(
            user.getId(),
            user.getName(),
            user.getEmail()
    );
}
@DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id){
    userService.deleteUser(id);
    return "User deleted successfully";
}
@PutMapping("/users/{id}")
public UserDTO updateUser(@PathVariable Long id, @RequestBody User updatedUser){
   User user = userService.updateUser(id, updatedUser);
   return new UserDTO(
           user.getId(),
           user.getName(),
           user.getEmail()
   );

}
@PostMapping("/auth/login")
  public ApiResponse login(@RequestBody LoginRequest loginRequest){
   return userService.login(loginRequest);
}
}



