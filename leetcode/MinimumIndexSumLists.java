// https://leetcode.com/problems/minimum-index-sum-of-two-lists/

class Solution {
    public String[] findRestaurant(String[] A, String[] B) {
        // B list string smallest index to compare with list A
        HashMap<String, Integer> setb = new HashMap<>();

        int N = B.length;
        for(int i = 0; i < N; i++) {
            if(!setb.containsKey(B[i])) {
                setb.put(B[i], i);
            }
        }

        // now compare list A
        int M = A.length;
        int min = N + M;
        HashMap<Integer, List<String>> sumMap = new HashMap<>();
        for(int i = 0; i < M; i++) {
            if(setb.containsKey(A[i])) {
                int s = i + setb.get(A[i]);
                if(!sumMap.containsKey(s)) {
                    sumMap.put(s, new ArrayList<>());
                }

                sumMap.get(s).add(A[i]);

                min = Math.min(min, s); // minimum sum index
            }
        }

        List<String> ans = sumMap.get(min);
        N = ans.size();
        String C[] = new String[N];
        for(int i = 0; i < N; i++) {
            C[i] = ans.get(i);
        }

        return C;
    }
}