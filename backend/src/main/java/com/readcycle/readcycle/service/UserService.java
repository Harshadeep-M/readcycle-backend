package com.readcycle.readcycle.service;

import com.readcycle.readcycle.dto.JwtResponse;
import com.readcycle.readcycle.dto.LoginRequest;
import com.readcycle.readcycle.dto.UserDTO;
import com.readcycle.readcycle.entity.User;
import com.readcycle.readcycle.exception.ResourceNotFoundException;
import com.readcycle.readcycle.repository.UserRepository;
import com.readcycle.readcycle.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAllUsers(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userRepository.findAll(pageable);

        List<UserDTO> userDTOList = new ArrayList<>();

        for (User user : userPage.getContent()) {
            UserDTO dto = new UserDTO(
                    user.getId(),
                    user.getName(),
                    user.getEmail()
            );

            userDTOList.add(dto);
        }

        return userDTOList;
    }

    public User createUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + id
                        )
                );
    }

    public void deleteUser(Long id) {

        userRepository.deleteById(id);
    }

    public User updateUser(Long id, User updatedUser) {

        User user = userRepository.findById(id).orElse(null);

        if (user != null) {

            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(
                    passwordEncoder.encode(updatedUser.getPassword())
            );

            return userRepository.save(user);
        }

        return null;
    }

    public JwtResponse login(LoginRequest loginRequest) {

        Optional<User> optionalUser =
                userRepository.findByEmail(loginRequest.getEmail());

        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }

        User user = optionalUser.get();

        boolean match = passwordEncoder.matches(
                loginRequest.getPassword(),
                user.getPassword()
        );

        if (!match) {
            throw new RuntimeException("Invalid Password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new JwtResponse(token, user.getId());
    }
}