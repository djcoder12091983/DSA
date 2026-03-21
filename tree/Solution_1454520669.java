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
    public void flatten(TreeNode root) {
        preorderFlatten(root);
    }

    TreeNode lastvisited = null; // last visited leaf node
    void preorderFlatten(TreeNode root) {
        if(root == null) {
            return;
        }

        lastvisited = root;
        preorderFlatten(root.left);

        TreeNode save = root.right;
        root.right = root.left;
        root.left = null;
        lastvisited.right = save;

        preorderFlatten(save);
    }
}