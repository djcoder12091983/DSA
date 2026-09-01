// https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/
// TODO -- wrong approach -- BUG FIX

/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};
*/

class Solution {

    // insert after node
    void insertAfter(Node node, int x) {
        Node next = node.next;
        Node newNode = new Node(x);
        node.next = newNode;
        newNode.next = next;
    }

    public Node insert(Node head, int insertVal) {
        if(head == null) {
            // edge case, size is 0
            Node newNode = new Node(insertVal);
            newNode.next = newNode;

            return newNode;
        }

        // TODO - I think we can skip this edge case handling
        /*
        if(insertVal >= head.val) {
            // we will start from next node of head
            // so we handle this case
            Node next = head.next;
            Node newNode = new Node(insertVal);
            head.next = newNode;
            newNode.next = next;

            return head;
        }
        */

        // we will keep on iterating and whenever we wwill find the suitable position
        // we will insert and we are done
        Node node = head;
        while(true) {
            if(insertVal >= node.val) {
                // normal flow
                insertAfter(node, insertVal);
                break; // we are done
            } else {
                if(node.val >= node.next.val && node.next.val >= insertVal) {
                    // this also can be a potential position
                    insertAfter(node, insertVal);
                    break; // we are done
                }
            }

            node = node.next;
        }

        return head; // we can return any head
    }
}