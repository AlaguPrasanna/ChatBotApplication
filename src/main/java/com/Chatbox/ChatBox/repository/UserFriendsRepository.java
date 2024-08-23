package com.Chatbox.ChatBox.repository;

import com.Chatbox.ChatBox.model.UserFriends;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFriendsRepository extends JpaRepository<UserFriends,Long> {
}
