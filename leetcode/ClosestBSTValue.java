// https://leetcode.com/problems/closest-binary-search-tree-value/

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

    // assuming left subtree will contains lesser and equals elements
    // right subtree contains greater

    // left side closest
    int left(TreeNode node, double T) {
        int max = Integer.MIN_VALUE;
        while(node != null) {
            int x = node.val;
            if(x <= T) {
                // will exist on right
                max = Math.max(max, x); // values less than x should be maximized
                node = node.right;
            } else {
                // take left
                node = node.left;
            }
        }

        return max;
    }

    // right side closest
    int right(TreeNode node, double T) {
        int min = Integer.MAX_VALUE;
        while(node != null) {
            int x = node.val;
            if(x < T) {
                // will exist on right
                node = node.right; // values greater than x should be minimized
            } else {
                // take left
                min = Math.min(min, x);
                node = node.left;
            }
        }

        return min;
    }

    public int closestValue(TreeNode root, double target) {
        // find the floor and ceil and return closest one
        // in case of tie we need to return smallest one
        int l = left(root, target);
        int r = right(root, target);

        if(l == Integer.MIN_VALUE) {
            // left not found, so right is the answer
            return r;
        }

        if(r == Integer.MAX_VALUE) {
            // right not found, so left is the answera
            return l;
        }

        double diff1 = target - l, diff2 = r - target;
        if(diff1 <= diff2) {
            return l;
        } else {
            return r;
        }
    }
}