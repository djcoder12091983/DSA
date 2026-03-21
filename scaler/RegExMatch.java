public class Solution {
    
    Map<String, Integer> DP = new HashMap<>();
    
    int solve(String A, String B, int a, int b) {
        
        // base case
        if(a < 0 && b < 0) {
            return 1;
        }
        
        if(b < 0) {
            // not possible
            return 0;
        }
        
        char y = B.charAt(b);
        if(a < 0) {
            if(y == '*') {
                // try to solve recursively
                return solve(A, B, a, b - 1);
            } else {
                return 0; // not possible
            }
        }
        char x = A.charAt(a);
        
        // look into DP
        String state = a+"-"+b;
        if(DP.containsKey(state)) {
            // already computed
            return DP.get(state);
        }
        
        int flag = 0;
        if(x == y || y == '?') {
            return solve(A, B, a - 1, b - 1); // recursive call
        }
        if(y == '*') {
            // possible recursive call (because * means any sequence inlcluding empty sequence)
            for(int i = 0; i <= a + 1; i++) {
                if(solve(A, B, a - i, b - 1) == 1) {
                    // possible
                    flag = 1;
                    break;
                }
            }
        }
        // store it for further use
        DP.put(state, flag);
        
        return flag;
    }
    
    // DO NOT MODIFY THE ARGUMENTS WITH "final" PREFIX. IT IS READ ONLY
    public int isMatch(final String A, final String B) {
        return solve(A, B, A.length() - 1, B.length() - 1);
    }
}