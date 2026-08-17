// https://leetcode.com/problems/take-k-of-each-character-from-left-and-right/

class Solution {

    public int takeCharacters(String S, int K) {
        
        int N = S.length();

        // always we need to look for 'a' 'b' 'c', so uniqueness is not required
        // TODO this code is required if instead of looking at 'a' 'b' 'c' if we need to look existing characters in S
        /*
        boolean unique[] = new boolean[3]; // we can extend it up to 26
        Arrays.fill(unique, false);

        for(int i = 0; i < N; i++) {
            char x = S.charAt(i);
            unique[x - 'a'] = true;
        }
        */

        // make this unique as all true
        boolean unique[] = new boolean[3]; // we can extend it up to 26
        Arrays.fill(unique, true);

        // pick elements from left side then see how many minimum characters to be picked from right side
        // for right side efficient checking we may use suffix indexing for each character

        HashMap<Integer, List<Integer>> position = new HashMap<>();

        // fill all character 'a' 'b' 'c' position as empty list
        for(int i = 0; i < 3; i++) {
            position.put(i, new ArrayList<>());
        }

        // TODO - 3 fixed size array of pointers
        // instead of hashmap we can use a fixed size array of pointer to list of position
        for(int i = N - 1; i >= 0; i--) {
            int x = S.charAt(i) - 'a';
            position.get(x).add(i);
        }

        // now start picking from left side
        int ans = -1;
        int[] freq = new int[3]; // track left side frequency
        Arrays.fill(freq, 0);
        // Note: only 3 characters it can be extended upto 26
        for(int i = -1; i < N; i++) {
            // need to start picking 0 from left side
            if(i >= 0) {
                char x = S.charAt(i);
                freq[x - 'a']++;
            }
            
            // now check how many characters we can pick from right side to fullfill requirements
            boolean possible = true;
            int minpick = N; // min pick from right side
            for(int j = 0; j < 3; j++) {
                if(unique[j] && freq[j] < K) {
                    // may need to pick from right side to fill remaining
                    List<Integer> poslist = position.get(j);
                    int req = K - freq[j];
                    if(req > poslist.size()) {
                        // not possible
                        possible = false;
                        break;
                    } else {
                        int idx = poslist.get(req - 1);
                        if(idx <= i) {
                            // not possible, left index cross right index
                            possible = false;
                            break;
                        } else {
                            // valid index and store the minimum so that it will be farthest minimum from right side
                            //System.out.println("2. Left: " + i + " => " + " Required: " + j + " Right: " + idx);
                            minpick = Math.min(minpick, idx);
                        }
                    }
                }
            }

            if(possible) {
                int total = i + 1;
                total += N - minpick;
                // pick possible from rigjt side
                if(ans == -1) {
                    // first time init
                    ans = total;
                } else {
                    // minimize answer
                    ans = Math.min(ans, total);
                }
            }
        }

        return ans;
    }
}