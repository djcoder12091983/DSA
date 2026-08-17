// https://leetcode.com/problems/remove-boxes/

class Solution {
    public int removeBoxes(int[] B) {
        // straight forward partition DP

        // mask is a boolean mask which helps to find which elements are remaining
        StringBuilder mask = new StringBuilder();
        int N = B.length;
        // all exist
        for(int i = 0; i < N; i++) {
            mask.append('1');
        }

        // now apply DP
        HashMap<String, Integer> DP = new HashMap<>();

        return solve(B, mask.toString(), DP);
    }

    // boolean mask generaion
    String generateMask(String mask, int start, int idx) {
        StringBuilder zeros = new StringBuilder();
        for(int j = start; j < idx; j++) {
            zeros.append('0');
        }
        return mask.substring(0, start) + zeros.toString() + mask.substring(idx);
    }

    int solve(int B[], String mask, HashMap<String, Integer> DP) {
        //System.out.println(mask);
        int N = B.length;
        boolean empty = true;
        for(int i = 0; i < N; i++) {
            if(mask.charAt(i) == '1') {
                empty = false;
                break; // elements remaining
            }
        }

        if(empty) {
            return 0; // no more items left
        }

        if(DP.containsKey(mask)) {
            return DP.get(mask); // already computed
        }

        // explore possible choices and find maximum one
        int i = 0;
        int prev = -1;
        int c = 0, start = -1;
        int maxscore = 0;
        while(i < N) {
            boolean select = mask.charAt(i) == '1';
            if(select) {
                if(B[i] == prev) {
                    c++; // consecutive same
                } else {
                    // reset and compute score

                    // compute score recurively
                    if(c > 0) {
                        // when no consecutive sequence exists then don't compute otherwise will fall into infinite call
                        // because mask will never change
                        String newmask = generateMask(mask, start, i);
                        maxscore = Math.max(maxscore, c*c + solve(B, newmask, DP));
                    }

                    // reset
                    start = i;
                    prev = B[i];
                    c = 1;
                }
            }

            i++;
        }

        // last sequence
        if(c > 0) {
            // when no consecutive sequence exists then don't compute otherwise will fall into infinite call
            // because mask will never change
            String newmask = generateMask(mask, start, i);
            maxscore = Math.max(maxscore, c*c + solve(B, newmask, DP));
        }

        // store result into DP
        DP.put(mask, maxscore);

        return maxscore;
    }
}