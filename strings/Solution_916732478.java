import java.math.BigInteger;

class Solution {
    public int myAtoi(String s) {

        String num = "";
        String sign = ""; 
        for(int i = 0; i < s.length(); i++) {
            char x = s.charAt(i);
            if(x == '+' || x == '-') {
                if(!sign.isEmpty() || !num.isEmpty()) {
                    // found +/- after digits or after +/- sign
                    break;
                }
                sign = sign + x;
            } else if(x >= '0' && x <= '9') {
                num = num + x;
            } else if(x == ' ') {
                // ignore white spaces
                if(!num.isEmpty() || !sign.isEmpty()) {
                    // if some numbers/sign read then break
                    break;
                } else {
                    // numbers/sign yet to read
                    continue;
                }
            } else {
                break; // alphabates then break the process
            }
        }

        if(num.isEmpty()) {
            num = "0";
        }
        num = sign + num;
        // large number
        BigInteger largenumber = new BigInteger(num);
        BigInteger MAX_VALUE = BigInteger.valueOf(Integer.MAX_VALUE);
        BigInteger MIN_VALUE = BigInteger.valueOf(Integer.MIN_VALUE);

        if(largenumber.compareTo(MAX_VALUE) > 0) {
            // handle overflow issue
            return MAX_VALUE.intValue();
        }
        if(largenumber.compareTo(MIN_VALUE) < 0) {
            return MIN_VALUE.intValue();
        }

        return largenumber.intValue();
        //return 0;        
    }
}