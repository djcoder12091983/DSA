
/*
struct ListNode { 
    int val; 
    ListNode *next,*random; 
    ListNode(int x) { 
        val = x; 
        next = random = NULL; 
    } 
}; 
*/
ListNode* clonelist(ListNode *head) {
    // first: clone linked list without random pointer
    ListNode *node = head;
    ListNode *new_head;
    new_head = new ListNode(node->val);
    ListNode *new_p = new_head;
    node = node->next;
    while(node) {
        new_p->next = new ListNode(node->val);
        new_p = new_p->next;
        node = node->next;
    }
    // second: point original list next pointer to clone list
    node = head;
    new_p = new_head;
    vector<ListNode *> t_nodes;
    while(node) {
        t_nodes.push_back(node); // save original nodes
        ListNode *t = node->next;
        node->next = new_p;
        node = t;
        new_p = new_p->next;
    }
    // third: point clone list random pointer to original list
    node = new_head;
    int i = 0;
    while(node) {
        node->random = t_nodes[i++];
        node = node->next;
    }
    // finally: manipulate random pointer
    node = new_head;
    while(node) {
        node->random = node->random->random->next;
        node = node->next;
    }
    
    return new_head;
}