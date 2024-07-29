package com.study.usedtrade.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentkey;

    @ManyToOne
    @JoinColumn(name = "userkey", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "itemkey", nullable = false)
    private Item item;
}
