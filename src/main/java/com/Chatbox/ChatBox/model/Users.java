package com.Chatbox.ChatBox.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String userName;
    private String userEmail;
    private String password;
    private LocalDateTime userCreatedAt;
    private LocalDateTime userUpdatedAt;
    private boolean isUserDeleted;

    @PrePersist
    protected void onUserCreate() {
        this.userCreatedAt = LocalDateTime.now();
        this.userUpdatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUserUpdate() {
        this.userUpdatedAt = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Friends> friendsList;


}
