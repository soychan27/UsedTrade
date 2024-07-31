package com.study.usedtrade.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addrkey;

    @ManyToOne
    @JoinColumn(name = "userkey", nullable = false)
    private User user;

    private String name;
    private String addressDetail;
    private String tel;
    private String req;
}
