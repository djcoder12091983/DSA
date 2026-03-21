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
    public boolean isSymmetric(TreeNode root) {
        return symmetric(root, root);
    }

    boolean symmetric(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null) {
            // both reach to null node then it's valid
            return true;
        }
        if(root1 == null || root2 == null || root1.val != root2.val) {
            return false; // one is NULL another one is not null or node value is not same
        }

        return symmetric(root1.left, root2.right) & symmetric(root1.right, root2.left);
    }
}