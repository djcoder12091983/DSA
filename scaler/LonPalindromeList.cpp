/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
int count_common(ListNode *A, ListNode *B) {
    int c = 0;
    while(A && B) {
        if(A->val == B->val) {
            ++c;
        } else {
            break;
        }
        A = A->next;
        B = B->next;
    }
    return c;
}

int Solution::solve(ListNode* head) {
    // ref: <gfg>/length-longest-palindrome-list-linked-list-using-o1-extra-space/
    int max_l = 0;
    ListNode *prev = NULL, *node = head;
    while(node) {
        ListNode *next = node->next;
        node->next = prev;
        // odd length check
        max_l = max(max_l, 2*count_common(prev, next) + 1);
        // even length check
        max_l = max(max_l, 2*count_common(node, next));
        
        prev = node;
        node = next;
    }
    return max_l;
}
