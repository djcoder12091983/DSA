// https://leetcode.com/problems/minimum-health-to-beat-game/


// biased thinking
class Solution {
    public long minimumHealth(int[] damage, int armor) {
        // can we think of BS or else we can think of some greedy approach like
        // to maximize the usage of armor we can choose close damage point of armor
        
        int max = 0;
        int N = damage.length;
        long total = 0;
        for(int i = 0; i < N; i++) {
            if(damage[i] <= armor) {
                max = Math.max(max, damage[i]);
            }

            total += damage[i];
        }

        if(total == 0) {
            // if total damage is 0 then return no need to use armour
            return 1;
        }

        if(max == 0) {
            // note: here 0 is invalid choice because if 0 the maximim which is <= armour and if we use armour in place 0
            // then it will be useless because full armour energy go waste rather we can use somewhere else 
            // not found or invalid choice, so we we will take the total and use that rmour anywhere
            // because all damage points are greater than armor
            return total - armor + 1;
        } else {
            return total - max + 1; // we will use armor at max point to maximize the armor usage
        }
    }
}