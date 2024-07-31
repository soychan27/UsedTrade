package com.study.usedtrade.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

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

    private String content;

    @Column(nullable = false)
    private Integer price;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime rdate;

    private Boolean complete;
    private String way;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemImage> images;
}