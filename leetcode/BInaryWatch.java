// https://leetcode.com/problems/binary-watch/

class Solution {

    public List<String> readBinaryWatch(int turnedOn) {
        
        int H = 0;
        List<String> ans = new ArrayList<>();
        while(H <= turnedOn) {

            List<Integer> hours = new ArrayList<>();
            List<Integer> minutes = new ArrayList<>();

            int M = turnedOn - H;
            // always H + M = turnedOn
            // hour combinations
            int l = 16;
            for(int i = 0; i < l; i++) {

                int bit = 0;
                // bit masking
                for(int j = 0; j < 4; j++) {
                    if((i & (1 << j)) > 0) {
                        bit++;
                    }
                }

                if(bit == H && i < 12) {
                    // expected number of bits and valid hour
                    hours.add(i);
                }
            }

            // same for minutes
            l = 64;
            for(int i = 0; i < l; i++) {

                int bit = 0;
                // bit masking
                for(int j = 0; j < 6; j++) {
                    if((i & (1 << j)) > 0) {
                        bit++;
                    }
                }

                if(bit == M && i < 60) {
                    // expected number of bits and valid minute
                    minutes.add(i);
                }
            }

            // now do the combinations
            for(int h : hours) {
                for(int m : minutes) {
                    String time = "" + h;
                    time += ":";
                    if(m < 10) {
                        time += "0" + m;
                    } else {
                        time += m;
                    }
                    
                    ans.add(time);
                }
            }

            H++;
        }

        return ans;
    }
}