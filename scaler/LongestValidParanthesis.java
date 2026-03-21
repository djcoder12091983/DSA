public class Solution {
    
    class Data {
        char symbol;
        int idx;
        
        Data(char symbol, int idx) {
            this.symbol = symbol;
            this.idx = idx;
        }
    }
    
    public int longestValidParentheses(String A) {
        // stack based DP approach
        int N = A.length();
        int DP[] = new int[N];
        Arrays.fill(DP, 0);
        
        Stack<Data> track = new Stack();
        int maxs = 0;
        for(int i = 0; i < N; i++) {
            char symbol = A.charAt(i);
            if(symbol == ')') {
                // nutralize if possible
                if(!track.isEmpty()) {
                    Data d = track.pop();
                    int idx = d.idx;
                    // valid paranteses sequence
                    DP[i] = (i - idx + 1) + (idx > 0 ? DP[idx - 1] : 0);
                    maxs = Math.max(maxs, DP[i]);
                }
            } else {
                track.push(new Data(symbol, i));
            }
        }
        
        return maxs;
    }
}
