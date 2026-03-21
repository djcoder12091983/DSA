/**
 * Definition for binary tree
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
typedef long long int ll;

ll dfs_sum(TreeNode *node, vector<ll> &sum) {
    if(node == NULL) {
        return 0L;
    }
    ll s = node->val + dfs_sum(node->left, sum) + dfs_sum(node->right, sum);
    sum.push_back(s);
    return s;
}

int Solution::solve(TreeNode* root) {
    vector<ll> sum;
    // avoid root node
    ll total = root->val + dfs_sum(root->left, sum) + dfs_sum(root->right, sum); // DFS sum
    int N = sum.size();
    for(int i = 0; i < N; i++) {
        if(sum[i] == total - sum[i]) {
            // other half found
            return true;
        }
    }
    return false;
}
