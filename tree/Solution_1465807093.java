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
    public boolean isValidBST(TreeNode root) {
        return valid(root, Long.MIN_VALUE, Long.MAX_VALUE); // long we have taken to avoid overflow issue +/-
    }

    boolean valid(TreeNode root, long left, long right) {
        if(root == null) {
            return true;
        }

        long x = root.val; // avoif overflow issue +/-
        if(x < left || x > right) {
            return false; // does not fall within range
        }

        return valid(root.left, left, x - 1) && valid(root.right, x + 1, right);
    }
}