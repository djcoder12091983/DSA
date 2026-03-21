class Solution {
     int[] dp = new int[301];
    private int help(int index, String str, Set<String> wDict ){
        
        if(index == str.length()) return 1;
        
        if(dp[index] != -1) return dp[index];
        
        String tempStr = "";
        for(int i = index; i < str.length(); i++){
            
            tempStr += str.charAt(i);
            
            if(wDict.contains(tempStr)){
                int verdict = help(i+1, str, wDict);
                if(verdict == 1) {
                    dp[index] = 1;
                    return dp[index];
                }
                
            }
        }
        dp[index] = 0;
        return dp[index];
    }
    public boolean wordBreak(String s, List<String> wordDict) {
        Arrays.fill(dp, -1);
        Set<String> wDict = new HashSet<>(wordDict);
        
        return help(0, s, wDict) == 1 ? true : false;
        
    }
}