package com.example.retailproductexample.controller;

import com.example.retailproductexample.data.MockDataStore;
import com.example.retailproductexample.model.Product;
import com.example.retailproductexample.service.FuzzySearchService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
  private final FuzzySearchService fuzzySearchService;

  public ProductController(FuzzySearchService fuzzySearchService) {
    this.fuzzySearchService = fuzzySearchService;
  }

  @PostMapping
  public ResponseEntity<String> addProduct(@RequestBody Product product) {
    MockDataStore.products.put(product.getId(), product);
    return ResponseEntity.ok("Product added successfully");
  }

  @GetMapping
  public ResponseEntity<List<Product>> getAllProducts() {
    return ResponseEntity.ok(MockDataStore.products.values().stream().toList());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable String id) {
    Product product = MockDataStore.products.get(id);
    if (product == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(product);
  }

  @GetMapping("/search")
  public ResponseEntity<List<Product>> searchProducts(@RequestParam String query) {
    List<Product> results = MockDataStore.products.values().stream()
        .filter(product -> fuzzySearchService.isFuzzyMatch(query, product.getName()))
        .collect(Collectors.toList());
    return ResponseEntity.ok(results);
  }

}