package com.example.retailproductexample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.example.retailproductexample.controller.ProductController;
import com.example.retailproductexample.data.MockDataStore;
import com.example.retailproductexample.model.Product;
import com.example.retailproductexample.service.FuzzySearchService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {FuzzySearchService.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ProductControllerTest {

  private ProductController productController;

  @Mock
  private FuzzySearchService fuzzySearchService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    this.productController = new ProductController(fuzzySearchService);
    MockDataStore.products.clear();
  }

  @Test
  void testAddProduct() {
    Product product = new Product("1", "Product A", "Category A", "Nice Sofa", 100.0, "image1.jpg");
    ResponseEntity<String> response = productController.addProduct(product);

    assertEquals(200, response.getStatusCode().value());
    assertEquals("Product added successfully", response.getBody());
    assertTrue(MockDataStore.products.containsKey("1"));
    assertEquals(product, MockDataStore.products.get("1"));
  }

  @Test
  void testGetAllProducts_NoProducts() {
    ResponseEntity<List<Product>> response = productController.getAllProducts();

    assertEquals(200, response.getStatusCode().value());
    assertTrue(response.getBody().isEmpty());
  }

  @Test
  void testGetAllProducts_WithProducts() {
    Product product = new Product("1", "Product A", "Category A", "Nice Sofa", 100.0, "image1.jpg");
    MockDataStore.products.put("1", product);

    ResponseEntity<List<Product>> response = productController.getAllProducts();

    assertEquals(200, response.getStatusCode().value());
    assertNotNull(response.getBody());
    assertEquals(1, response.getBody().size());
    assertEquals(product, response.getBody().getFirst());
  }

  @Test
  void testGetProductById_ProductExists() {
    Product product = new Product("1", "Product A", "Category A", "Nice Sofa", 100.0, "image1.jpg");
    MockDataStore.products.put("1", product);

    ResponseEntity<Product> response = productController.getProductById("1");

    assertEquals(200, response.getStatusCode().value());
    assertEquals(product, response.getBody());
  }

  @Test
  void testGetProductById_ProductNotFound() {
    ResponseEntity<Product> response = productController.getProductById("999");

    assertEquals(404, response.getStatusCode().value());
  }

  @Test
  void testSearchProducts_NoMatches() {
    when(fuzzySearchService.isFuzzyMatch(anyString(), anyString())).thenReturn(false);

    ResponseEntity<List<Product>> response = productController.searchProducts("Product");

    assertEquals(200, response.getStatusCode().value());
    assertNotNull(response.getBody());
    assertTrue(response.getBody().isEmpty());
  }

  @Test
  void testSearchProducts_WithMatches() {
    Product product1 = new Product("1", "Product A", "Category A", "Nice Sofa", 100.0, "image1.jpg");
    Product product2 = new Product("2", "Another Product", "Description B", "Nice bed", 200.0, "image2.jpg");
    MockDataStore.products.put("1", product1);
    MockDataStore.products.put("2", product2);

    when(fuzzySearchService.isFuzzyMatch("Product A", product1.getName())).thenReturn(true);
    when(fuzzySearchService.isFuzzyMatch("Product", product2.getName())).thenReturn(false);

    ResponseEntity<List<Product>> response = productController.searchProducts("Product A");

    assertEquals(200, response.getStatusCode().value());
    assertNotNull(response.getBody());
    assertEquals(1, response.getBody().size());
    assertEquals(product1, response.getBody().getFirst());
  }

}
