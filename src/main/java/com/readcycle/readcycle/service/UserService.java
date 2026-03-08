package com.readcycle.readcycle.service;
import com.readcycle.readcycle.entity.User;
import com.readcycle.readcycle.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public User createUser(User user){
        return userRepository.save(user);
    }
    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);

    }
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
    public User updateUser(Long id, User updatedUser){
        User user = userRepository.findById(id).orElse(null);
        if(user != null){
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            return userRepository.save(user);



        }
        return null;
    }
}

