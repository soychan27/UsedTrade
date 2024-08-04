package com.study.usedtrade.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer replykey;

    @ManyToOne
    @JoinColumn(name = "userkey", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "itemkey", nullable = false)
    private Item item;

    private String content;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime created_at;
}
