package com.example.retailproductexample.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Product {
  private String id;
  private String name;
  private String category;
  private String description;
  private double price;
  private String imageUrl;

}
