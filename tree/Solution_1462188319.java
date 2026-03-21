/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    TreeNode ans = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        find(root, p.val, q.val);
        return ans;
    }

    // idea is to find two values on opposite subtree
    int find(TreeNode root, int x, int y) {

        if(ans != null) {
            // ans found, no need to find it
            return 0;
        }

        if(root == null) {
            return 0;
        }

        int c = 0;
        if(root.val == x || root.val == y) {
            c += 1;
        }

        c += find(root.left, x, y) + find(root.right, x, y);

        if(c == 2 && ans == null) {
            // found two nodes and ans not set yet
            ans = root;
        }

        return c;

    }
}