package com.Chatbox.ChatBox.controller;

import com.Chatbox.ChatBox.dto.ApiResponse;
import com.Chatbox.ChatBox.dto.UserDTO;
import com.Chatbox.ChatBox.model.Users;
import com.Chatbox.ChatBox.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signUp")
    @Validated
    public ResponseEntity<ApiResponse<UserDTO>> createUser(@RequestBody @Validated UserDTO userDTO)
    {
        try {
            UserDTO createdUser = userService.createUser(userDTO);
            ApiResponse<UserDTO> response = new ApiResponse<>(HttpStatus.OK.value(), "User registered successfully", createdUser);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<UserDTO> response = new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


}
