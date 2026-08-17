// https://leetcode.com/problems/remove-boxes/

class Solution {
    public int removeBoxes(int[] B) {
        // straight forward partition DP

        // now apply DP, making input as key
        HashMap<List<Integer>, Integer> DP = new HashMap<>();

        int N = B.length;
        List<Integer> data = new ArrayList<>(N);
        for(int i = 0; i < N; i++) {
            data.add(B[i]);
        }

        return solve(data, DP);
    }

    List<Integer> generate(List<Integer> data, int start, int idx) {
        int N = data.size();
        int M = N - (idx - start);
        List<Integer> newdata = new ArrayList<>(M);

        for(int i = 0; i < start; i++) {
            newdata.add(data.get(i));
        }
        for(int i = idx; i < N; i++) {
            newdata.add(data.get(i));
        }

        return newdata;
    }

    int solve(List<Integer> data, HashMap<List<Integer>, Integer> DP) {
        //System.out.println(mask);
        int N = data.size();
        if(N == 0) {
            return 0;
        }

        if(DP.containsKey(data)) {
            return DP.get(data);
        }

        // explore possible choices and find maximum one
        int i = 0;
        int prev = -1, start = -1;
        int c = 0;
        int maxscore = 0;
        while(i < N) {
            if(data.get(i) == prev) {
                c++; // consecutive same
            } else {
                // reset and compute score

                // compute score recurively
                if(c > 0) {
                    // when no consecutive sequence exists then don't compute otherwise will fall into infinite call
                    // because array will never change
                    List<Integer> newdata = generate(data, start, i);
                    maxscore = Math.max(maxscore, c*c + solve(newdata, DP));
                }

                // reset
                start = i;
                prev = data.get(i);
                c = 1;
            }

            i++;
        }

        // last sequence
        if(c > 0) {
            // when no consecutive sequence exists then don't compute otherwise will fall into infinite call
            // because array will never change
            List<Integer> newdata = generate(data, start, i);
            maxscore = Math.max(maxscore, c*c + solve(newdata, DP));
        }

        // store result into DP
        DP.put(data, maxscore);

        return maxscore;
    }
}