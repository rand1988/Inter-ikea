package com.example.retailproductexample.service;

import org.springframework.stereotype.Service;

@Service
public class FuzzySearchService {
   /*
    * Calculate the edit distance between two strings using dynamic programming.
     The edit distance is the minimum number of operations required to transform
     one string into the other. The allowed operations are insertion, deletion, and
     substitution of a single character.
     isFuzzyMatch("kitten", "sitting")
     Edit distance between "kitten" and "sitting":
     Substitute 'k' → 's': 1 edit
     Substitute 'e' → 'i': 2 edits
     Insert 'g' at the end: 3 edits
     Total: 3
     Fuzzy match threshold:
     Math.max(1, 6 / 3) = 2
     Result:
     Edit distance (3) > Threshold (2), so the result is false.
   */

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
