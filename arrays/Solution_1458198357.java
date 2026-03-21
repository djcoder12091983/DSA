/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> Q) {
        
        // flat the tree into sorted array
        ArrayList<Integer> A = new ArrayList<>();
        inorder(root, A);

        // answer the queries
        int N = Q.size();
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        for(int i = 0; i < N; i++) {
            int x = Q.get(i);
            int f = floor(A, x);
            int c = ceil(A, x);

            List<Integer> d = new ArrayList<>();
            d.add(f);
            d.add(c);

            ans.add(d);
        }

        return ans;
    }

    int floor(ArrayList<Integer> A, int x) {
        int l = 0, h = A.size() - 1;
        int ans = -1;
        while(l <= h) {
            int m = (l + h)/2;
            if(A.get(m) < x) {
                // move right
                l = m + 1;
                ans = A.get(m); // potential answer
            } else if(A.get(m) > x) {
                // move left
                h = m - 1;
            } else {
                // best answer
                return A.get(m);
            }
        }

        return ans;
    }

    int ceil(ArrayList<Integer> A, int x) {
        int l = 0, h = A.size() - 1;
        int ans = -1;
        while(l <= h) {
            int m = (l + h)/2;
            if(A.get(m) < x) {
                // move right
                l = m + 1;
            } else if(A.get(m) > x) {
                // move left
                h = m - 1;
                ans = A.get(m); // potential answer
            } else {
                // best answer
                return A.get(m);
            }
        }

        return ans;
    }

    void inorder(TreeNode root, ArrayList<Integer> A) {
        if(root == null) {
            return;
        }

        inorder(root.left, A);
        A.add(root.val);
        inorder(root.right, A);
    }
}