package com.study.usedtrade.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderkey;

    @ManyToOne
    @JoinColumn(name = "userkey", nullable = false)
    private User user;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private LocalDateTime date;
}
