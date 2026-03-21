class Solution {
    public int minimumSwap(String s1, String s2) {
        
        // possible pairs <X, Y> <Y, X>
        // we can combine two <X,Y> or <Y,X> it needs one swap, but if we combine <X,Y> or <Y,X> then needs two swaps
        // so first we will do self combine then do cross combine

        int N = s1.length();
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i < N; i++) {
            char ch1 = s1.charAt(i);
            char ch2 = s2.charAt(i);
            if(ch1 == ch2) {
                continue; // it's already same
            }
            String pair = ch1 + "" + ch2;

            map.put(pair, map.getOrDefault(pair, 0) + 1);
        }

        int swaps = 0;
        int c1 = 0, c2 = 0;
        if(map.containsKey("xy")) {
            c1 = map.get("xy");
            swaps += c1/2; // self combine
        }
        if(map.containsKey("yx")) {
            c2 = map.get("yx");
            swaps += c2/2; // self combine
        }

        int r1 = c1 % 2, r2 = c2 % 2;
        if(r1 == r2) {
            // always will be 1 or 0
            // if they both are 0 then no more swaps required or else we need 2 more swap
            swaps += r1*2;
        } else {
            return -1; // if one is 0 another is 1 then no way possible
        }

        return swaps;
    }
}