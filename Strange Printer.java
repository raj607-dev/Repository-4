class Solution {
  public int strangePrinter(String s) {
    final int n = s.length();
    int[][] mem = new int[n][n];
    return strangePrinter(s, 0, n - 1, mem);
  }

  // Recursive helper method for strangePrinter
  private int strangePrinter(String s, int i, int j, int[][] mem) {
    if (i > j)
      return 0;
    if (mem[i][j] > 0)
      return mem[i][j];

    // Print s[i].
    mem[i][j] = strangePrinter(s, i + 1, j, mem) + 1;

    for (int k = i + 1; k <= j; k++)
      if (s.charAt(k) == s.charAt(i))
        mem[i][j] = Math.min(mem[i][j], strangePrinter(s, i, k - 1, mem) + //
                                            strangePrinter(s, k + 1, j, mem));

    return mem[i][j];
  }
}

Approach 2: Bottom-up¶
Time: 
O
(
n
3
)
O(n 
3
 )
Space: 
O
(
n
2
)
O(n 
2
 )

C++
Java
class Solution {
 public:
  int strangePrinter(string s) {
    if (s.empty())
      return 0;

    const int n = s.size();
    // dp[i][j] := the minimum number of turns to print s[i..j]
    vector<vector<int>> dp(n, vector<int>(n, n));

    for (int i = 0; i < n; ++i)
      dp[i][i] = 1;

    for (int j = 0; j < n; ++j)
      for (int i = j; i >= 0; --i)
        for (int k = i; k < j; ++k)
          dp[i][j] = min(dp[i][j], dp[i][k] + dp[k + 1][j] - (s[k] == s[j]));

    return dp[0][n - 1];
  }
};
