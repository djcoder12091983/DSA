class Solution:
    def uniquePathsWithObstacles(self, obstacleGrid: List[List[int]]) -> int:
        dp = []
        m = len(obstacleGrid)
        n = len(obstacleGrid[0])
        for i in range(m):
            row = []
            for j in range(n):
                row.append(0)
            dp.append(row)
        for i in range(m):
            for j in range(n):
                if obstacleGrid[i][j] == 1:
                    dp[i][j] = 0
                elif i == 0 and j == 0:
                    # base case
                    dp[i][j] = 1
                else:
                    dp[i][j] = 0
                    if i > 0:
                        dp[i][j] += dp[i-1][j]
                    if j > 0:
                        dp[i][j] += dp[i][j-1]
        return dp[m - 1][n - 1]