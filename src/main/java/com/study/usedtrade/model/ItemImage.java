package com.study.usedtrade.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ItemImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imagekey;

    @ManyToOne
    @JoinColumn(name = "itemkey", nullable = false)
    private Item item;

    @Column(nullable = false)
    private String imageName;

    @Column(nullable = false)
    private String imagePath;
}
