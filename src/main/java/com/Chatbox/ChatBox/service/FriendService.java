package com.Chatbox.ChatBox.service;

import com.Chatbox.ChatBox.dto.UserDTO;
import com.Chatbox.ChatBox.model.Friends;
import com.Chatbox.ChatBox.model.UserFriends;
import com.Chatbox.ChatBox.model.Users;
import com.Chatbox.ChatBox.repository.FriendRepository;
import com.Chatbox.ChatBox.repository.UserFriendsRepository;
import com.Chatbox.ChatBox.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FriendService {
    @Autowired
    private FriendRepository friendsRepository;

    @Autowired
    private UserFriendsRepository userFriendsRepository;

    @Autowired
    private UserRepository userRepository;

    public void addFriends(List<UserDTO> friendsList) throws Exception {
        Optional<Users> userOptional = userRepository.findById(1L); // Assuming only one user

        if (userOptional.isPresent()) {
            Users user = userOptional.get();

            for (UserDTO friendDto : friendsList) {
                Friends friend = new Friends();
                friend.setFriendName(friendDto.getUserName());
                friend.setFriendEmail(friendDto.getUserEmail());
                friend.setUser(user);

                Friends savedFriend = friendsRepository.save(friend);

                UserFriends userFriend = new UserFriends();
                userFriend.setUser(user);
                userFriend.setFriend(savedFriend);

                userFriendsRepository.save(userFriend);
            }
        } else {
            throw new Exception("User not found");
        }
    }
}
