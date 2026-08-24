// https://leetcode.com/problems/two-sum-iv-input-is-a-bst/

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

    boolean findPair(TreeNode node, int k) {
        while(node != null) {
            if(node.val == k) {
                return true;
            }

            if(node.val > k) {
                // go left
                node = node.left;
            } else {
                node = node.right;
            }
        }

        return false; // not found
    }

    public boolean findTarget(TreeNode root, int k) {
        return findTarget(root, k, root);
    }

    boolean findTarget(TreeNode node, int k, TreeNode root) {
        if(node == null) {
            return false;
        }

        int req = k - node.val;
        if(req != node.val) {
            // because all values are unique
            boolean found = findPair(root, req);
            // System.out.println(node.val + " -> " + req + " Found: " + found);
            if(found) {
                // found pair sum
                return true;
            }
        }

        // now left and right call
        return findTarget(node.left, k, root) | findTarget(node.right, k, root);
    }
}