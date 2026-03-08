package com.readcycle.readcycle;
import com.readcycle.readcycle.entity.User;
import com.readcycle.readcycle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HelloController {
@Autowired
private UserService userService;
@GetMapping("/users")
public List<User> getAllUsers(){
    return userService.getAllUsers();
}
@PostMapping("/users")
    public User createUser(@RequestBody User user){
    return userService.createUser(user);
}
@GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id){
    return userService.getUserById(id);
}
@DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id){
    userService.deleteUser(id);
    return "User deleted successfully";
}
@PutMapping("/users/{id}")
public User updateUser(@PathVariable Long id, @RequestBody User updatedUser){
    return userService .updateUser(id, updatedUser);
}
    }


