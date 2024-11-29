package com.example.retailproductexample.service;

import org.springframework.stereotype.Service;

@Service
public class FuzzySearchService {

  public boolean isFuzzyMatch(String query, String target) {
    int distance = calculateEditDistance(query.toLowerCase(), target.toLowerCase());
    return distance <= Math.max(1, query.length() / 3);
  }

  private int calculateEditDistance(String a, String b) {
    int[][] dp = new int[a.length() + 1][b.length() + 1];

    for (int i = 0; i <= a.length(); i++) {
      for (int j = 0; j <= b.length(); j++) {
        if (i == 0) {
          dp[i][j] = j;
        } else if (j == 0) {
          dp[i][j] = i;
        } else if (a.charAt(i - 1) == b.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
        }
      }
    }
    return dp[a.length()][b.length()];
  }

}
