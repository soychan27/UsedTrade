package com.study.usedtrade.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemkey;

    @ManyToOne
    @JoinColumn(name = "userkey", nullable = false)
    private User user;

    @Column(nullable = false)
    private String name;

    private String img1;
    private String img2;
    private String img3;
    private String img4;
    private String img5;
    private String content;

    @Column(nullable = false)
    private Integer price;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime rdate;

    private Boolean complete;
    private String way;
}
