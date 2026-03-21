class Solution {
    public int partitionString(String s) {
        int i = 0;
        HashSet<Character> unique = new HashSet<>();
        int N = s.length();
        int p = 1;
        while(i < N) {
            char x = s.charAt(i);
            if(unique.contains(x)) {
                // put a partition
                p++;
                // reset set
                unique = new HashSet<Character>();
            }

            unique.add(x);
            i++;
        }
        return p;
    }
}