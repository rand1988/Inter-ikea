package com.example.retailproductexample.data;

import com.example.retailproductexample.model.Product;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MockDataStore {
  public static final Map<String, Product> products = new ConcurrentHashMap<>();

  static {
    products.put("1", new Product("1", "Soffa", "ART", "KARLSTAD cvr c23/32 Linneryd natural GB", 1500.00, "68196_PE182348_S4.jpg"));
    products.put("2", new Product("2", "Rug", "ART", "KNARDRUP rug low pile 170x240 yellow", 200.00, "0720934_PE732943_S4.JPG"));
    products.put("3", new Product("3", "Bed", "ART", "FILIPSTAD cvr pnl 62x240 brown", 200.00, "0308795_PE428392_S4.JPG"));
  }
}
