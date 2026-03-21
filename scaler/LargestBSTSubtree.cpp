/**
 * Definition for binary tree
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
typedef struct recursive_data {
    int nodes, max_node, min_node, node_val;
    bool bst;
    recursive_data(int nodes, int max_node, int min_node, bool bst) {
        this->nodes = nodes;
        this->node_val = node_val;
        this->max_node = max_node;
        this->min_node = min_node;
        this->bst = bst;
    }
} recursive_data;

recursive_data dfs_bst(TreeNode *node, int &max_bst) {
    if(node == NULL) {
        // base condition
        return recursive_data(0, INT_MIN, INT_MAX, true);
    }
    bool bst = true;
    // initial BST check
    if(node->left) {
        bst &= node->left->val < node->val;
    }
    if(node->right) {
        bst &= node->right->val > node->val;
    }
    // recursive call
    recursive_data left = dfs_bst(node->left, max_bst);
    recursive_data right = dfs_bst(node->right, max_bst);
    // further BST check
    bst &= left.max_node < node->val && right.min_node > node->val;
    bst &= left.bst & right.bst;
    
    int nodes = 1 + left.nodes + right.nodes;
    if(bst) {
        max_bst = max(max_bst, nodes);
    }
    int max_node = max(node->val, max(left.max_node, right.max_node));
    int min_node = min(node->val, min(left.min_node, right.min_node));
    return recursive_data(nodes, max_node, min_node, bst);
}

int Solution::solve(TreeNode* root) {
    int max_bst = 0;
    dfs_bst(root, max_bst);
    return max_bst;
}
