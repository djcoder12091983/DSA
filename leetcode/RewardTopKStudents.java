// https://leetcode.com/problems/reward-top-k-students/

class Solution {

    int compare(int s1[], int s2[]) {
        int c = s2[1] - s1[1];
        if(c != 0) {
            return c;
        }

        return s1[0] - s2[0]; // is same score then sort based on id
    }

    public List<Integer> topStudents(String[] positive, String[] negative, String[] report, int[] id, int k) {
        Set<String> plus = new HashSet<>();
        Set<String> minus = new HashSet<>();

        int N = positive.length;
        for(int i = 0; i < N; i++) {
            plus.add(positive[i]);
        }

        N = negative.length;
        for(int i = 0; i < N; i++) {
            minus.add(negative[i]);
        }

        N = report.length;
        int score[][] = new int[N][2];
        
        for(int i = 0; i < N; i++) {
            String tokens[] = report[i].split(" ");
            
            int l = tokens.length;
            int s = 0;
            for(int j = 0; j < l; j++) {
                if(plus.contains(tokens[j])) {
                    s += 3;
                } else if(minus.contains(tokens[j])) {
                    s -= 1;
                }
            }

            int idx = id[i];

            score[i][0] = idx;
            score[i][1] = s;
        }

        Arrays.sort(score, (x, y) -> compare(x, y));

        // top k result
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < k; i++) {
            ans.add(score[i][0]);
        }

        return ans;
    }
}