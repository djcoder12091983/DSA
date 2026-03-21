import java.math.BigDecimal;

class Solution {

    BigDecimal zero = new BigDecimal(0);
    BigDecimal n255 = new BigDecimal(255);

    boolean ipv4TokenValidation(String token) {
        int l = token.length();
        if(l == 0) {
            return false;
        }
        if(l > 1 && token.charAt(0) == '0') {
            // check for leading 0's
            return false;
        }
        for(int i = 0; i < l; i++) {
            if(!Character.isDigit(token.charAt(i))) {
                return false;
            }
        }
        BigDecimal x = new BigDecimal(token);
        if(x.compareTo(zero) < 0 || x.compareTo(n255) > 0) {
            return false;
        }
        return true;
    }

    boolean checkHexCode(char x) {
        if(x >= 'A' && x <= 'F') {
            return true;
        }
        if(x >= 'a' && x <= 'f') {
            return true;
        }
        return false;
    }

    boolean ipv6TokenValidation(String token) {
        int l = token.length();
        if(l == 0 || l > 4) {
            return false;
        }
        for(int i = 0; i < l; i++) {
            char x = token.charAt(i);
            if(!Character.isDigit(x) && !checkHexCode(x)) {
                return false;
            }
        }
        return true;
    }

    public String validIPAddress(String queryIP) {

        String types[] = {"IPv4", "IPv6", "Neither"};
        
        String tokens[] = null;
        if(queryIP.contains(".")) {
            // possibly IPV4
            tokens = queryIP.split("\\.", -1);
        } else if(queryIP.contains(":")) {
            // possibly IPV6
            tokens = queryIP.split(":", -1);
        } else {
            return types[2];
        }
        int l = tokens.length;
        if(l == 4) {
            // IPV4 validations
            for(int i = 0; i < l; i++) {
                if(!ipv4TokenValidation(tokens[i])) {
                    return types[2];
                }
            }
            return types[0];
        } else if(l == 8) {
            // IPV6 validations
            for(int i = 0; i < l; i++) {
                if(!ipv6TokenValidation(tokens[i])) {
                    //System.out.println("IPV6 Invalid: " + tokens[i]);
                    return types[2];
                }
            }
            return types[1];
        } else {
            //System.out.println("Issue: " + queryIP);
            return types[2];
        }
    }
}