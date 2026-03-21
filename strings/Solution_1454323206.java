class Solution {
    public List<String> restoreIpAddresses(String s) {

        return restore(s, 4);
        
    }

    public List<String> restore(String s, int req) {

        List<String> ans = new ArrayList<>();

        int n = s.length();
        if(n > req * 3) {
            return ans; // no way possible, empty list
        }

        if(req == 1) {
            if(s.charAt(0) == '0') {
                if(n > 1) {
                    return ans; // leading 0s
                }
            }
            int num = Integer.parseInt(s);
            if(num <= 255) {
                ans.add(s); // valid
            }
            return ans;
        }
        
        // try out all possibilities
        for(int i = 1; i < n; i++) {
            String t = s.substring(0, i);
            if(t.charAt(0) == '0' && t.length() > 1) {
                continue; // leading 0s
            }
            if(t.length() > 3) {
                continue; // no way possible
            }
            int num = Integer.parseInt(t);
            if(num <= 255) {
                // valid
                List<String> res = restore(s.substring(i, n), req - 1); // recursively generate
                if(res.size() > 0) {
                    // found
                    for(String x : res) {
                        ans.add(t + "." + x);
                    }
                }
            }
        }

        return ans;

    }
}