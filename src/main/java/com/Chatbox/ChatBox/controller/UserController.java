package com.Chatbox.ChatBox.controller;

import com.Chatbox.ChatBox.dto.ApiResponse;
import com.Chatbox.ChatBox.dto.LoginDTO;
import com.Chatbox.ChatBox.dto.UserDTO;
import com.Chatbox.ChatBox.model.Users;
import com.Chatbox.ChatBox.service.FriendService;
import com.Chatbox.ChatBox.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private FriendService friendService;

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
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserDTO>> loginUser(@RequestBody @Validated LoginDTO loginDTO) {
        try {
            UserDTO loggedInUser = userService.loginUser(loginDTO);
            ApiResponse<UserDTO> response = new ApiResponse<>(HttpStatus.OK.value(), "Login successful", loggedInUser);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<UserDTO> response = new ApiResponse<>(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/addfriends")
    public ResponseEntity<ApiResponse<String>> addFriends(@RequestBody List<UserDTO> friendsList) {
        try {
            friendService.addFriends(friendsList);
            ApiResponse<String> response = new ApiResponse<>(HttpStatus.OK.value(), "Friends added successfully", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<String> response = new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }


}}
