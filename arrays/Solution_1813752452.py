class Solution:

    def __init__(self):
        self.dp = {}

    def maximize(self, A, idx):
        n = len(A)
        if idx >= n:
            return 0

        if idx in self.dp:
            return self.dp[idx]

        mx = 0
        for i in range(idx, n):
            mx = max(mx, A[i] + self.maximize(A, i + 2)) # skip next element

        self.dp[idx] = mx
        return mx

    def rob(self, nums: List[int]) -> int:
        return self.maximize(nums, 0)