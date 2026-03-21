import heapq as pq

class Solution(object):
    def kthSmallest(self, A, k):
        
        n = len(A)
        min_pq = []
        for i in range(n):
            pq.heappush(min_pq, (A[i][0], i, 0))

        ans = -1
        while k > 0:
            data = pq.heappop(min_pq)
            ans = data[0]
            row = data[1]
            col = data[2]

            if col < n - 1:
                pq.heappush(min_pq, (A[row][col + 1], row, col + 1))

            k -= 1

        return ans

        