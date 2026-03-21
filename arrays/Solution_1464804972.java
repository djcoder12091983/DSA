class Solution {
    public List<String> fullJustify(String[] W, int max) {
        
        int i = 0;
        int N = W.length;

        List<String> ans = new ArrayList<>();
        while(i < N) {
            
            int j = i;
            int space = W[j].length();
            j++;
            while(j < N) {
                int t = space + 1 + W[j].length();
                if(t > max) {
                    break;
                }
                space = t;
                j++;
            }

            StringBuilder row = new StringBuilder();
            if(j == N || j - i == 1) {
                // this is last line or a single word, in both cases behavior is same
                row.append(W[i]);
                for(int k = i + 1; k < j; k++) {
                    row.append(" ").append(W[k]); // normal justification, left to right
                }
                // extra space on right
                for(int k = space; k < max; k++) {
                    row.append(" ");
                }

                ans.add(row.toString()); // add last line

                if(j == N) {
                    break; // we are done with last line
                }
            } else {
                // fully justify
                // compute space distribution
                space = max - space + (j - i - 1);
                int slot = (int)Math.ceil((1.0 * space) / (j - i - 1));

                // full justification
                row.append(W[i]);
                for(int k = i + 1; k < j; k++) {
                    int t;
                    if(space % (j - k) == 0) {
                        t = space / (j - k);
                    } else {
                        t = slot;
                    }
                    space = space - t;
                    for(int l = 0; l < t; l++) {
                        row.append(" ");
                    }
                    row.append(W[k]);
                }

                ans.add(row.toString()); // add line
            }

            i = j; // next line process
        }

        return ans;
    }
}