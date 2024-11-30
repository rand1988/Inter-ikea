package com.example.retailproductexample;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.retailproductexample.service.FuzzySearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FuzzySearchServiceTest {

  private FuzzySearchService fuzzySearchService;

  @BeforeEach
  void setUp() {
    fuzzySearchService = new FuzzySearchService();
  }

  @Test
  void testExactMatch() {
    assertTrue(fuzzySearchService.isFuzzyMatch("apple", "apple"), "Exact match should return true");
  }

  @Test
  void testCaseInsensitiveMatch() {
    assertTrue(fuzzySearchService.isFuzzyMatch("Apple", "apple"), "Case-insensitive match should return true");
  }

  @Test
  void testFuzzyMatchWithOneEdit() {
    assertTrue(fuzzySearchService.isFuzzyMatch("appl", "apple"), "Single character difference should return true");
  }

  @Test
  void testFuzzyMatchWithTwoEdits() {
    assertTrue(fuzzySearchService.isFuzzyMatch("appls", "apple"), "Two character differences should return true for short strings");
  }

  @Test
  void testFuzzyMatchWithThreshold() {
    assertTrue(fuzzySearchService.isFuzzyMatch("applic", "apple"), "Edits within the threshold should return true");
  }

  @Test
  void testNonFuzzyMatch() {
    assertFalse(fuzzySearchService.isFuzzyMatch("banana", "apple"), "Completely different strings should return false");
  }

  @Test
  void testEmptyQuery() {
    assertFalse(fuzzySearchService.isFuzzyMatch("", "apple"), "Empty query should return false");
  }

  @Test
  void testEmptyTarget() {
    assertFalse(fuzzySearchService.isFuzzyMatch("apple", ""), "Empty target should return false");
  }

  @Test
  void testBothEmpty() {
    assertTrue(fuzzySearchService.isFuzzyMatch("", ""), "Both empty strings should return true");
  }

  @Test
  void testLargeStringMismatch() {
    assertFalse(fuzzySearchService.isFuzzyMatch("supercalifragilisticexpialidocious", "apple"),
        "Completely different large strings should return false");
  }

  @Test
  void testSubstringMatch() {
    assertFalse(fuzzySearchService.isFuzzyMatch("app", "apple"), "Substring within the threshold should return true");
  }

}
