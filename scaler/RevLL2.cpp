/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
void reverse(ListNode** head) {
    ListNode *prev = NULL, *current = *head, *next;
    while(current) {
        // move the direction of pointer
        next = current->next;
        current->next = prev;
        prev = current;
        current = next;
    }
    *head = prev;
}

ListNode* Solution::reverseBetween(ListNode* head, int B, int C) {
    ListNode *dummy = new ListNode(0); // dummy head for easy computation
    dummy->next = head;
    int i = 1;
    ListNode *node = dummy;
    while(i++ < B) {
        node = node->next;
    }
    ListNode *t_head = node->next, *prev_node = node;
    node->next = NULL; // disconnects from first half
    // copy next elements and reverse it
    i = B;
    node = t_head;
    while(i++ < C) {
        node = node->next;
    }
    ListNode *t_end = node->next;
    node->next = NULL; // disconnects from third half
    // reverse separated linked list
    reverse(&t_head);
    
    // connect three halves
    prev_node->next = t_head;
    node = t_head;
    while(1) {
        if(node->next == NULL) {
            // connects with third half
            node->next = t_end;
            break;
        }
        node = node->next;
    }
    
    return dummy->next; // avoid dummy node
}
