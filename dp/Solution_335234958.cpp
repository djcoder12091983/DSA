/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
    private:
    int max_sum = INT_MIN;
    
    // dfs call
    int dfs_call(TreeNode* node) {
        if(node == NULL) {
            return 0;
        }
        int left_sum = dfs_call(node->left);
        int right_sum = dfs_call(node->right);
        int val = node->val;
        int node_max_path = max(val, max(val + left_sum, val + right_sum));
        max_sum = max(max_sum, max(node_max_path, val + left_sum + right_sum));
        
        // return maximum sum so far
        return node_max_path;
    }
public:
    int maxPathSum(TreeNode* root) {
        dfs_call(root);
        return max_sum;
    }
};