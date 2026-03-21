public class Solution {
    
    class Group {
        char x;
        int c;
        
        Group(char x, int c) {
            this.x = x;
            this.c = c;
        }
    }
    
    // compress the string
    List<Group> compress(String S) {
        int l = S.length();
        char t = S.charAt(0);
        int c = 1;
        List<Group> groups = new ArrayList<>();
        for(int i = 1; i < l; i++) {
            char x = S.charAt(i);
            if(t != x) {
                // sequence break
                groups.add(new Group(t, c));
                
                // reset
                t = x;
                c = 1;
            } else {
                c++;
            }
        }
        
        // last
        groups.add(new Group(t, c));
        
        return groups;
    }
    
    // B can be formed from A
    int match(List<Group> A, List<Group> B) {
        int l = A.size();
        if(l != B.size()) {
            // not possible
            return 0;
        }
        for(int i = 0; i < l; i++) {
            Group ga = A.get(i);
            Group gb = B.get(i);
            
            if(ga.x != gb.x || ga.c > gb.c) {
                // not possible
                return 0;
            }
        }
        return 1; // not violation
    }
    
    public int solve(ArrayList<String> A, String B) {
        List<Group> compressed = compress(B);
        int N = A.size();
        for(int i = 0; i < N; i++) {
            int f = match(compress(A.get(i)), compressed);
            if(f == 1) {
                // possible
                return 1;
            }
        }
        
        return 0; // no match found
    }
}
