// https://leetcode.com/problems/find-unique-binary-string/

class Solution {
    public String findDifferentBinaryString(String[] A) {
        // either we can generatye all possible string having same length
        // then check for duplicacy

        int len = A[0].length();
        if(len == 1) {
            // edge case
            return A[0].equals("0") ? "1" : "0";
        }

        // but if we go with prefix then then it would be easy to find unique one
        int N = A.length;
        Set<String> set = new HashSet<>();
        for(int i = 0; i < N; i++) {
            set.add(A[i]);
        }

        // we will keep on generating all possible unique binary strings of same length
        Queue<String> Q = new LinkedList<>();
        Q.add("0");
        Q.add("1");
        
        int l  = 1;
        while(l < len - 1) {
            int s = Q.size();
            for(int i = 0; i < s; i++) {
                String x = Q.poll();
                Q.add(x + "0");
                Q.add(x + "1");
            }

            l++;
        }

        // now we will add 0/1 every available string having length - 1 in the Q
        l = Q.size();
        for(int i = 0; i < l; i++) {
            String x = Q.poll();
            if(!set.contains(x + "0")) {
                // found one
                return x + "0";
            }

            if(!set.contains(x + "1")) {
                // found one
                return x + "1";
            }
        }

        return ""; // dummy return, it will always find missing one
    }
}