package com.study.usedtrade.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userkey;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;

    private String role;
    private String tel;
    private String provider;

    @Column(name="provider_id")
    private String providerId;

    @Builder
    public User(String username, String password, String email, String role, String provider,
                String providerId, String tel, String nickname){
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
        this.tel = tel;
        this.nickname = nickname;
    }
}
