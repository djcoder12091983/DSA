/**
 * Definition for binary tree
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
void inorder_traversal(TreeNode *node, vector<int> &inorder) {
    if(node->left) {
        inorder_traversal(node->left, inorder);
    }
    inorder.push_back(node->val);
    if(node->right) {
        inorder_traversal(node->right, inorder);
    }
}

bool bs(vector<int> &sorted, int left, int right, int X) {
    int start = left, end = right;
    while(start <= end) {
        int mid = (start + end) / 2;
        if(sorted[mid] == X) {
            return true;
        } else if(X < sorted[mid]) {
            // take left
            end = mid -1;
        } else {
            // take right
            start = mid + 1;
        }
    }
    return false;
}

int Solution::t2Sum(TreeNode* root, int B) {
    vector<int> sorted;
    // sort
    inorder_traversal(root, sorted);
    
    // find pair
    int N = sorted.size();
    for(int i = 0; i < N - 1; i++) {
        int a = sorted[i];
        int b = B - a; // required
        if(bs(sorted, i + 1, N - 1, b)) {
            return 1;
        }
    }
    return 0; // not possible
}
