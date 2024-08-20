package com.Chatbox.ChatBox.service;

import com.Chatbox.ChatBox.controller.UserController;
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
    public Users createUser(Users user) throws Exception
    {
        if(!validationService.isValidEmail(user.getUserEmail()))
        {
            throw new Exception("Invalid email format");
        }
        Optional<Users> existingUserOptional = userRepository.findByUserEmail(user.getUserEmail());

        if (existingUserOptional.isPresent())
        {
            throw new Exception("Email is already registered");
        }

        return userRepository.save(user);

    }
}
