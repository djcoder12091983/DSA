/**
 * Definition for binary tree
 * class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) {
 *      val = x;
 *      left=null;
 *      right=null;
 *     }
 * }
 */
public class Solution {
    
    class DummyTreeNode {
        int val;
        TreeNode original; // original node
        //int id; // node ID
        long maxSum = 0L; // maximum path sum on node
        long sum = 0L; // BFS sum
        DummyTreeNode left, right;
        
        DummyTreeNode(TreeNode node, int val) {
            this.original = node;
            this.val = val;
            //this.id = id;
        }
    }
    
    //int id = 1;
    
    // copy the original tree (DFS)
    DummyTreeNode copy(TreeNode node) {
        DummyTreeNode root = new DummyTreeNode(node, node.val);
        if(node.left != null) {
            root.left = copy(node.left);
        }
        if(node.right != null) {
            root.right = copy(node.right);
        }
        return root;
    }
    
    // DFS call to calculate max path sum on node
    long maxPathSum(DummyTreeNode node) {
        long left = 0, right = 0;
        if(node.left != null) {
            left = maxPathSum(node.left);
        }
        if(node.right != null) {
            right = maxPathSum(node.right);
        }
        int x = node.val;
        node.maxSum = Math.max(left + x, right + x);
        return node.maxSum;
    }
    
    public TreeNode solve(TreeNode T, long B) {
        // copy original tree
        DummyTreeNode root = copy(T);
        // max path sum calculation
        maxPathSum(root);
        if(root.maxSum < B) {
            // full tree remove
            return null;
        }
        
        // BFS to check which node to remove or not
        Queue<DummyTreeNode> bfs = new LinkedList<>();
        root.sum = root.val;
        bfs.add(root);
        while(!bfs.isEmpty()) {
            DummyTreeNode node = bfs.poll();
            if(node.left != null) {
                long max = node.left.maxSum;
                if(node.sum + max < B) {
                    // full subtree delink
                    node.original.left = null;
                } else {
                    // explore subtree where to remove
                    node.left.sum = node.sum + node.left.val;
                    bfs.add(node.left);
                }
            }
            if(node.right != null) {
                long max = node.right.maxSum;
                if(node.sum + max < B) {
                    // full subtree delink
                    node.original.right = null;
                } else {
                    // explore subtree where to remove
                    node.right.sum = node.sum + node.right.val;
                    bfs.add(node.right);
                }
            }
        }
        return root.original;
    }
}
