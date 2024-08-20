package com.Chatbox.ChatBox.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "Friends")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Friends {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long friendId;
    private String friendName;
    private String friendEmail;
    private LocalDateTime friendCreatedAt;
    private LocalDateTime friendUpdatedAt;
    private boolean isFriendDeleted;

    @PrePersist
    protected void onFriendCreate()
    {
        this.friendCreatedAt=LocalDateTime.now();
        this.friendUpdatedAt=LocalDateTime.now();
    }
    @PreUpdate
    protected void onFriendUpdate()
    {
        this.friendUpdatedAt=LocalDateTime.now();
    }
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userId")
    @JsonIgnore
    private Users user;


}
