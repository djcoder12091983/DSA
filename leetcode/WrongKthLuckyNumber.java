// https://leetcode.com/problems/find-the-k-th-lucky-number/

class Solution {
    public String kthLuckyNumber(int k) {

        if(k == 1) {
            return "4";
        }

        // find simulated tree height then we can find the kth item in the tree
        // as the tree complecte and full binary tree with 4 7 two branches
        int h = 0;
        int p = 0;
        while(p < k) {
            h++;
            p = p + (1 << h);
        }

        // System.out.println("Height: " + h);

        char ans[] = new char[h];
        int i = 0;
        while(k > 0) {
            int half = (1 << h) - 1; // total nodes half
            // System.out.println("H: " + h + " Half: " + half);
            // if k is > half then it will fall into second half otherwise first half
            if(k <= half) {
                // left part
                ans[i++] = '4';
            } else {
                // right part
                ans[i++] = '7';
            }

            k = k - half - 1; // remaining k we will work on
            h--; // modfied height as it shrinks by one
        }

        return new String(ans);
    }
}