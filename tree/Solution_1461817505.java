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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int x = Math.min(p.val, q.val), y = Math.max(p.val, q.val);
        int z = root.val;
        
        if(x <= z && y >= z) {
            // x and y on opposite subtree
            return root;
        } else if(z > y) {
            // move left side
            return lowestCommonAncestor(root.left, p, q);
        } else {
            // move right side
            return lowestCommonAncestor(root.right, p, q);
        }
    }
}