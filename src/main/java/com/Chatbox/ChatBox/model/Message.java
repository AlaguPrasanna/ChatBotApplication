package com.Chatbox.ChatBox.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Message")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    private String lastMessage;

    @OneToOne
    @JoinColumn(name = "user_friend_id")
    private UserFriends userFriendId;

    private Boolean read;
    private LocalDateTime messageCreatedAt;
    private LocalDateTime messageModifiedAt;

    @PrePersist
    protected void onMessageCreate() {
        this.messageCreatedAt = LocalDateTime.now();
        this.messageModifiedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onMessageUpdate() {
        this.messageModifiedAt = LocalDateTime.now();
    }
}
