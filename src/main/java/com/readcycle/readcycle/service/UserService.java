package com.readcycle.readcycle.service;
import com.readcycle.readcycle.entity.User;
import com.readcycle.readcycle.exception.ResourceNotFoundException;
import com.readcycle.readcycle.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.readcycle.readcycle.dto.UserDTO;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<UserDTO> getAllUsers(){
        List<User> users  = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for(User user : users){
            UserDTO dto = new UserDTO(
                    user.getId(),
                    user.getName(),
                    user.getEmail()

            );
            userDTOList.add(dto);
        }
        return userDTOList;
    }
    public User createUser(User user){
        return userRepository.save(user);
    }
    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: "+ id));

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

