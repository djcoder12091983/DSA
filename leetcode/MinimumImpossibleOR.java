// https://leetcode.com/problems/minimum-impossible-or/description/

class Solution {
    public int minImpossibleOR(int[] A) {
        // Found one pattern which can be also thought of from Prefix Tree
        // TODO need to write detailed explanation behind prefix tree and teh logic

        // look for 2 power if that 2 power exists then look for next 2 power
        // TODO need to explain this in detailed way

        HashSet<Integer> track = new HashSet<>();
        for(int x : A) {
            track.add(x);
        }

        int p2 = 1;
        while(true) {
            if(!track.contains(p2)) {
                // find the smallest one
                return p2;
            }

            p2 *= 2;
        }

        // return -1; // this won't happen as per test cases
    }
}