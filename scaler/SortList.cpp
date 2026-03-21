/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
 // merge two sorted list
ListNode *merge(ListNode *L, ListNode *R) {
    if(!L){
        return R;
    }
    if(!R) {
        return L;
    }
    ListNode *h = NULL;
    if(L->val < R->val) {
        h = L;
        h->next = merge(L->next, R);
    } else {
        h = R;
        h->next = merge(L, R->next);
    }
    return h;
}

ListNode* Solution::sortList(ListNode* head) {
    if(!head || !head->next) {
        // single element
        return head;
    }
    // get middle one
    ListNode *slow = head, *fast = head->next; 
    while(fast && fast->next) {
        slow = slow->next;
        fast = fast->next->next;
    }
    // divide recursively and then merge
    ListNode *left = head, *right = slow->next;
    slow->next = NULL;
    left = sortList(left);
    right = sortList(right);
    return merge(left, right);
}
