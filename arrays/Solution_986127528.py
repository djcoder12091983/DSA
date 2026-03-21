class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:

        N = len(nums)
        self.res = []
        self.calcpermute(nums, 0)
        return self.res

    def calcpermute(self, A, i):
        N = len(A)
        if i == N:
            curr = []
            for i in range(N):
                curr.append(A[i])
            self.res.append(curr)
            return

        for idx in range(i, N):
            
            A[i], A[idx] = A[idx], A[i]
            self.calcpermute(A, i + 1)
            A[i], A[idx] = A[idx], A[i]