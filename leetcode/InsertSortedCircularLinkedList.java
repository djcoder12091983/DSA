// https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/
// TODO -- can we write better like handling less condition -- more generalization

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

        if(head.next == head) {
            // size is 1
            Node newNode = new Node(insertVal);
            head.next = newNode;
            newNode.next = head;

            return head;
        }

        // we will keep on iterating and whenever we wwill find the suitable position
        // we will insert and we are done
        Node node = head;
        Node lastNode = null;
        boolean found = false;
        while(node.next != head) {
            if(node.val <= insertVal && node.next.val >= insertVal) {
                // normal flow
                insertAfter(node, insertVal);
                found = true;
                break; // we are done
            } else if(node.val > node.next.val) {
                if(insertVal >= node.val || insertVal <= node.next.val) {
                    // rotation point
                    insertAfter(node, insertVal);
                    found = true;
                    break; // we are done
                }
            }

            lastNode = node;
            node = node.next;
        }

        if(!found) {
            // position not found, then we can safely insert after last
            insertAfter(node, insertVal);
        }

        return head; // we can return any head
    }
}