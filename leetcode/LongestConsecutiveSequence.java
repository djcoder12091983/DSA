// https://leetcode.com/problems/longest-consecutive-sequence/

class Solution {
    public int longestConsecutive(int[] nums) {

        if(nums.length == 0) {
            return 0; // edge case
        }

        // will wprk on unique values for better implementation
        HashSet<Integer> A = new HashSet<>();
        for(int x : nums) {
            A.add(x);
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        // add with 1 length
        for(int x : A) {
            map.put(x, 1);
        }

        int maxlen = 1;
        for(int x : A) {
            int left = x - 1;
            if(map.containsKey(left)) {
                int len1 = map.get(left);
                int len2 = map.get(x);
                int len = len1 + len2;
                map.put(left - len1 + 1, len);
                map.put(x + len2 - 1, len);

                //System.out.println("LEFT: [" + x + "]" + map);

                maxlen= Math.max(maxlen, len);
            }
            // avoid both way combine otherwise it will count twice
            /*int right = x + 1;
            if(map.containsKey(right)) {
                int len1 = map.get(right);
                int len2 = map.get(x);
                int len = len1 + len2;
                map.put(right + len1 - 1, len);
                map.put(x - len2 + 1, len);

                // System.out.println("RIGHT: [" + x + "]" + map);

                maxlen = Math.max(maxlen, len);
            }*/
        }

        System.out.println(map);

        return maxlen;
    }
}