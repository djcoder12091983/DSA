/**
 * Definition for binary tree
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
int recursive_node_sum(TreeNode *node) {
    if(node == NULL) {
        return 0;
    }
    return node->val + recursive_node_sum(node->left) + recursive_node_sum(node->right);
}

int Solution::solve(TreeNode* node) {
    if(node == NULL) {
        return 1; // base case
    }
    if(node->left == NULL && node->right == NULL) {
        return 1; // base case
    }
    bool ok = solve(node->left) && solve(node->right);
    if(ok) {
        int left = recursive_node_sum(node->left);
        int right = recursive_node_sum(node->right);
        return node->val == left + right;
    } else {
        return false;
    }
}
