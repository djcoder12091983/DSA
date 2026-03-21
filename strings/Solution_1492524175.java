class Solution {
    public String decodeString(String s) {
        ArrayList<String> t = new ArrayList<>();
        for(int i = 0; i < s.length(); i++) {
            char x = s.charAt(i);
            if((x != ']')) {
                // non closing bracket, keep on adding to list
                t.add(x + "");
            } else {
                // find close opening bracket and exapnd it
                String temp = "";
                while(!t.get(t.size() - 1).equals("[")) {
                    temp = t.get(t.size() - 1) + temp;
                    t.remove(t.size() - 1);
                }
                t.remove(t.size() - 1); // remove opening bracket
                // extract number before opening bracket
                String temp1 = "";
                while(t.size() > 0) {
                    char d = t.get(t.size() - 1).charAt(0);
                    if(d >= '0' && d <= '9') {
                        temp1 = d + temp1;
                    } else {
                        break;
                    }
                    t.remove(t.size() - 1);
                }
                int num = Integer.parseInt(temp1);
                // exapnd the string with number
                String temp2 = "";
                for(int j = 0; j < num; j++) {
                    temp2 = temp2 + temp;
                }
                // push back to array list again
                t.add(temp2);
            }
        }
        // leftover is my answer, combine all leftovers
        String ans = "";
        for(String x : t) {
            ans = ans + x;
        }
        return ans;
    }
}