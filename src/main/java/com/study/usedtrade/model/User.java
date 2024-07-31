package com.study.usedtrade.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userkey;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;

    private String role;
    private String tel;
    private String provider;
    private String provider_id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items;

    @Builder
    public User(String username, String password, String email, String role, String provider,
                String providerId, String tel, String nickname){
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.provider_id = providerId;
        this.tel = tel;
        this.nickname = nickname;
    }
}

