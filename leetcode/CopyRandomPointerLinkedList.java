// https://leetcode.com/problems/copy-list-with-random-pointer/
class Solution {
    public Node copyRandomList(Node head) {
        if(head == null) {
            return null; // edge case (empty linked list)
        }
        Node node = head;
        // old to new node mapping for random pointer update in O(1)
        // otherwise we might need to travel in the original link list get random pointer position
        // so that we can update in new linked list accordingly
        while(node != null) {
            Node newnode = new Node(node.val);
            Node next = node.next;
            // change the link from old to new one which avoids using map (old to new)
            node.next = newnode; // old to new mapping
            newnode.next = next; // helps to get old next node

            node = node.next.next; // not old next node comes via new node mapping
        }

        // update random pointer efficiently in O(1)
        node = head;
        while(node != null) {
            if(node.random != null) {
                // if random pointer is not null
                node.next.random = node.random.next;
            }
            
            // move to next
            node = node.next.next;
        }

        // save the newnode head
        // we will restore the next pointers of both linked list
        Node ans = head.next;
        node = head;

        while(node != null) {
            Node node1 = node.next;
            node.next = node1.next; // restore original linked list next node

            if(node.next != null) {
                // if it's not last not node
                node1.next = node.next.next;
            }

            node = node.next; // move to next node
        }

        return ans; // new linked list head
    }
}