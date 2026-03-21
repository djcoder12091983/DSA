class Solution {
    
    List<List<Integer>> ans;
    
    void find(int[] A, int B, int idx, int sum, ArrayList<Integer> temp) {
        if(sum == B) {
            // found the answer
            ans.add(new ArrayList<Integer>(temp));
            return;
        }
        if(sum > B) {
            // stop explosring further recursions
            return;
        }
        
        // recursions
        HashSet<Integer> unique = new HashSet<>();
        for(int i = idx; i < A.length; i++) {
            if(unique.contains(A[i])) {
                continue; // avoid duplicates
            }
            
            unique.add(A[i]);
            
            temp.add(A[i]);
            find(A, B, i + 1, sum + A[i], temp);
            temp.remove(temp.size() - 1); // back track
        }
    }
    
    public List<List<Integer>> combinationSum2(int[] A, int B) {
        ans = new ArrayList<>();
        
        Arrays.sort(A);
        find(A, B, 0, 0, new ArrayList<Integer>());
        
        return ans;
    }
}