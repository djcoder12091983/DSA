// https://leetcode.com/problems/finding-3-digit-even-numbers/

class Solution {

    // convert digits to number
    int convert(int d[]) {
        int num = 0, p10 = 1;
        for(int i = 2; i >= 0; i--) {
            num += d[i] * p10;
            p10 = p10 * 10;
        }

        return num;
    }

    void generate(int d[], int idx, List<Integer> ans) {

        if(idx == 3) {
            // if last digit is even
            if(d[idx -1] % 2 == 0) {
                ans.add(convert(d));
            }

            return; // valid permutation
        }

        boolean v[] = new boolean[10];
        Arrays.fill(v, false);
        
        int N = d.length;
        for(int i = idx; i < N; i++) {
            
            boolean select = true;
            if(idx == 0 && d[i] == 0) {
                select = false;
            }
            if(v[d[i]] == true) {
                select = false;
            }

            if(select) {

                // mark it visited
                // so that next we will not choose it to avoid duplicates
                v[d[i]] = true;
                
                // swap
                int t = d[idx];
                d[idx] = d[i];
                d[i] = t;
                
                generate(d, idx + 1, ans);

                // re-swap backtrack
                t = d[idx];
                d[idx] = d[i];
                d[i] = t;
            }
        }
    }

    public int[] findEvenNumbers(int[] digits) {
        
        if(digits.length < 3) {
            return new int[]{}; // empty array
        }
        
        List<Integer> ans = new ArrayList<>();
        generate(digits, 0, ans);

        Collections.sort(ans); // sort the answer

        int N = ans.size();
        int numbers[] = new int[N];
        for(int i = 0; i < N; i++) {
            numbers[i] = ans.get(i);
        }

        return numbers;
    }
}