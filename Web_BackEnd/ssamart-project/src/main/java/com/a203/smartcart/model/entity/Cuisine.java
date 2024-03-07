package com.a203.smartcart.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@Table(name = "cuisine")
public class Cuisine {

    @Id
    @Column(name = "cuisine_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq; // 추천 요리 id

    @Column(name = "cuisine_name")
    private String name;

    @Column(name = "cuisine_image")
    private String image;

    @Column(name= "cuisine_stepImage")
    private String stepImage;

    @OneToMany(mappedBy = "cuisine")
    private List<Recipe> recipeList;

}
