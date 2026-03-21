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
    Map<Integer, TreeNode> roots = new HashMap<>();
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {

        if(root == null) {
            return new ArrayList<TreeNode>(); // empty list
        }

        Set<Integer> delete = new HashSet<>();
        for(int x : to_delete) {
            delete.add(x);
        }

        roots.put(root.val, root); // root itself will be part of answer

        separate(root, delete, null, 'N');
        return new ArrayList<TreeNode>(roots.values()); // roots contain all separated rot nodes
    }

    // tree traversal and when a node is deleted then it's child will be separated
    void separate(TreeNode root, Set<Integer> delete, TreeNode parent, char type) {
        if(root == null) {
            return;
        }

        TreeNode left = root.left;
        TreeNode right = root.right;

        int x = root.val;
        if(delete.contains(x)) {
            // already added to roots then remove it as well
            roots.remove(x);
            // remove the edges
            root.left = null;
            root.right = null;
            // remove the parent to child edge
            if(type == 'L') {
                parent.left = null;
            }
            if(type == 'R') {
                parent.right = null;
            }

            // separate
            if(left != null) {
                roots.put(left.val, left);
            }

            if(right != null) {
                roots.put(right.val, right);
            }

        }

        // left and right traversal
        separate(left, delete, root, 'L');
        separate(right, delete, root, 'R');
    }
}