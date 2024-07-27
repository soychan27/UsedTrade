package com.study.usedtrade.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer messagekey;

    @ManyToOne
    @JoinColumn(name = "senderkey", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiverkey", nullable = false)
    private User receiver;

    @Column(nullable = false)
    private String content;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime senttime;
}
