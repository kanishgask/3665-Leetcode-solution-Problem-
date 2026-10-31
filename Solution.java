
class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int MOD = 1_000_000_007;

        // Landing position after entering a cell from each direction
        int[][] right = new int[m][n];
        int[][] down = new int[m][n];

        // Precompute mirror reflections
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {

                int pos = i * n + j;

                if (grid[i][j] == 0) {
                    right[i][j] = pos;
                    down[i][j] = pos;
                } else {
                    // Entering mirror while moving right:
                    // Reflect down
                    right[i][j] = (i + 1 < m)
                            ? down[i + 1][j] : -1;

                    // Entering mirror while moving down:
                    // Reflect right
                    down[i][j] = (j + 1 < n)
                            ? right[i][j + 1] : -1;
                }
            }
        }

        long[][] dp = new long[m][n];
        dp[0][0] = 1;

        // Count paths
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (dp[i][j] == 0) {
                    continue;
                }

                long ways = dp[i][j];

                // Try moving right
                if (j + 1 < n) {
                    int dest = right[i][j + 1];

                    if (dest != -1) {
                        int r = dest / n;
                        int c = dest % n;

                        dp[r][c] = (dp[r][c] + ways) % MOD;
                    }
                }

                // Try moving down
                if (i + 1 < m) {
                    int dest = down[i + 1][j];

                    if (dest != -1) {
                        int r = dest / n;
                        int c = dest % n;

                        dp[r][c] = (dp[r][c] + ways) % MOD;
                    }
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }
}
