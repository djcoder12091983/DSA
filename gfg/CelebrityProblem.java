

// User function Template for Java

class Solution {
    // Function to find if there is a celebrity in the party or not.
    public int celebrity(int A[][]) {
        // code here
        // person's indegree and outdegree map
        // 0th index indegree and 1th index is outdegree 
        Map<Integer, int[]> map = new HashMap<>();
        int N = A.length;
        for(int i = 0; i < N; i++) {
            map.put(i, new int[]{0,0});
        }
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(A[i][j] == 1) {
                    map.get(j)[0]++; // increase indegree for j
                    map.get(i)[1]++; // increases outdegree for i
                }
            }
        }
        
        for(int i = 0; i < N; i++) {
            if(map.get(i)[0] == N - 1 && map.get(i)[1] == 0) {
                // known to all but does not know anyone
                return i;
            }
        }
        
        return -1;
    }
}