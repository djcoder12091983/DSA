// https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/
// TODO misunderstood the question - head may not be always the minimum node

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
    public Node insert(Node head, int insertVal) {

        if(head == null) {
            // edge case, size is 0
            Node newNode = new Node(insertVal);
            newNode.next = newNode;

            return newNode;
        }

        // to make operation easy first we will detach the circular link
        // then after operation we reattach the link again
        Node node = head.next;
        Node lastNode = null;
        if(node == head) {
            node.next = null;
            lastNode = head;
        } else {
            while(node != null) {
                if(node.next == head) {
                    lastNode = node;
                    node.next = null;
                }

                node = node.next;
            }
        }

        // if insert vaule < head
        if(insertVal < head.val) {
            Node newNode = new Node(insertVal);
            newNode.next = head;
            lastNode.next = newNode;
            
            return newNode; // newNode is the head
        } else {
            Node t = head;
            while(t != null) {
                if(insertVal >= t.val) {
                    Node next = t.next;
                    Node newNode = new Node(insertVal);
                    t.next = newNode;
                    newNode.next = next;

                    if(next == null) {
                        lastNode = newNode; // if inserted after lastNode
                    }

                    lastNode.next = head; // reattach the head

                    break; // we are done
                }

                t = t.next;
            }

            return head; // head will not change
        }
    }
}