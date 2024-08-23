package com.Chatbox.ChatBox.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "UserFriend")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserFriends {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userFriendId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "friend_id")
    private Friends friend;

    private LocalDateTime userFriendCreatedAt;
    private LocalDateTime userFriendUpdatedAt;
    private Boolean isDeleted;

    @PrePersist
    protected void onUserFriendCreate() {
        this.userFriendCreatedAt = LocalDateTime.now();
        this.userFriendUpdatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUserFriendUpdate() {
        this.userFriendUpdatedAt = LocalDateTime.now();
    }

    @OneToOne(mappedBy = "userFriendId", cascade = CascadeType.ALL)
    private Message message;
}
