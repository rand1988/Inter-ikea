package com.example.retailproductexample.data;

import com.example.retailproductexample.model.Product;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MockDataStore {
  public static final Map<String, Product> products = new ConcurrentHashMap<>();

  static {
    products.put("1", new Product("1", "Laptop", "Electronics", "High-performance laptop", 1500.00, "image1.jpg"));
    products.put("2", new Product("2", "Headphones", "Electronics", "Noise-cancelling headphones", 200.00, "image2.jpg"));
  }
}
