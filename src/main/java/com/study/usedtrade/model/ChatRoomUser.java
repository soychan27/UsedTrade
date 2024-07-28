package com.study.usedtrade.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ChatRoomUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cruserkey;

    @ManyToOne
    @JoinColumn(name = "roomkey", nullable = false)
    private ChatRoom room;

    @ManyToOne
    @JoinColumn(name = "userkey", nullable = false)
    private User user;
}
