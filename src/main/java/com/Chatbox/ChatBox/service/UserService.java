package com.Chatbox.ChatBox.service;

import com.Chatbox.ChatBox.controller.UserController;
import com.Chatbox.ChatBox.dto.LoginDTO;
import com.Chatbox.ChatBox.dto.UserDTO;
import com.Chatbox.ChatBox.model.Users;
import com.Chatbox.ChatBox.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;
    public UserDTO createUser(UserDTO userDTO) throws Exception {
        if (!validationService.isValidEmail(userDTO.getUserEmail())) {
            throw new Exception("Invalid email format");
        }

        Optional<Users> existingUserOptional = userRepository.findByUserEmail(userDTO.getUserEmail());
        if (existingUserOptional.isPresent()) {
            throw new Exception("Email is already registered");
        }

        Users user = new Users();
        user.setUserName(userDTO.getUserName());
        user.setUserEmail(userDTO.getUserEmail());
        user.setPassword(userDTO.getPassword());

        Users savedUser = userRepository.save(user);
        return new UserDTO(savedUser.getUserId(), savedUser.getUserName(), savedUser.getUserEmail(), null);
    }

    public UserDTO loginUser(LoginDTO loginDTO) throws Exception {
        Optional<Users> userOptional = userRepository.findByUserEmail(loginDTO.getUserEmail());

        if (userOptional.isEmpty()) {
            throw new Exception("Invalid email or password");
        }

        Users user = userOptional.get();

        if (!user.getPassword().equals(loginDTO.getPassword())) {
            throw new Exception("Invalid email or password");
        }

        return new UserDTO(user.getUserId(), user.getUserName(), user.getUserEmail(), null);
    }
    }

