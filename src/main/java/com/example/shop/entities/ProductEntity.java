package com.example.shop.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nameOfProduct;
    private String description;
    private Integer price;
    private Integer amount;
    private String picture;


}
