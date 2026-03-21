/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
ListNode* new_node(int v) {
    ListNode* node = new ListNode(v);
    node->next = NULL;
    return node;
}

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

ListNode* Solution::reorderList(ListNode* head) {
    // note: split linked list into halves then reverse second half
    // now merge two halves
    
    // find mid
    ListNode *slow = head, *fast = slow->next;
    while(fast && fast->next) {
        slow = slow->next;
        fast = fast->next->next;
    }
    // split linked list in two halves 
    ListNode* head1 = head;
    ListNode* head2 = slow->next;
    slow->next = NULL;
    
    reverse(&head2); // reverse second half
  
    // merge alternate nodes 
    head = new_node(0); // dummy Node 
  
    ListNode* current = head;
    while(head1 || head2) {
        // take it from first
        if(head1) {
            current->next = head1;
            current = current->next;
            head1 = head1->next;
        }
        // take it from second 
        if(head2) {
            current->next = head2;
            current = current->next;
            head2 = head2->next;
        }
    }
  
    // skip dummy node
    return head->next;
}
