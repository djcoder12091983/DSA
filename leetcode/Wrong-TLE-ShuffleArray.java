// https://leetcode.com/problems/shuffle-an-array/
// TODO -- ned to check behavior whether random generates whole set of number
// within a range before it generates duplicates
// TODO -- also this approach will give TLE if all permutations are returned
// and then still suffle calls
// TODO -- so we will think of generating possible permutations <= 10000
// which is expected number of times suffle called

class Solution {

    // this is a kind od BRUTE FORCE approach
    // like everytime we will generate suffle array with new random object
    // having different seeds by default, if it constructs same suffle array again
    // then we may need to check duplicacy and return new one

    // track hashcode of generated suffle array
    // TODO -- it may happen two differenr suffles can generate same hashcode
    Set<Integer> unique = new HashSet<>();
    int original[];
    int suffle[];

    public Solution(int[] A) {
        original = A;
        suffle = A;
    }
    
    public int[] reset() {
        suffle = original;
        return original;
    }
    
    public int[] shuffle() {

        // either we can apply suffle on original array or previous suffle array
        // here we are doing suffle on suffle array
        
        int N = original.length;
        int ans[] = new int[N];
        boolean found = false;
        
        while(!found) {
            Random random = new Random();
            for(int i = 0; i < N; i++) {
				// TODO -- ned to check behavior whether random generates whole set of number
				// within a range before it generates duplicates
                ans[i] = suffle[random.nextInt(N)];
            }

            int hash = Arrays.hashCode(ans);
            if(!unique.contains(hash)) {
                // TODO -- it may happen two differenr suffles can generate same hashcode
                unique.add(hash);
                found = true;
            }
        }

        suffle = ans;

        return suffle;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */