

/* Node of a linked list
 class Node {
   int data;
    Node next;
    Node(int d)  { data = d;  next = null; }
}
 Linked List class
class LinkedList
{
    Node head;  // head of list
}*/

class Intersect {
    // Function to find intersection point in Y shaped Linked Lists.
    int intersectPoint(Node head1, Node head2) {
        // code here
        int n1 = 0;
        Node h1 = head1;
        while (h1 != null){
            n1++; h1 = h1.next ;
        }
       int n2 = 0;
       Node h2 = head2;
       while (h2 != null){
           n2++; h2 = h2.next ;
       }
       h1 = head1;
       h2 = head2;
       if (n1>n2){
           n1 = n1-n2;
           while (n1-- >0 && h1 != null){
               h1 = h1.next;
           }
           while (h1 != null && h2 != null){
               if (h1 == h2){
                   return h1.data;
               }
               h1 = h1.next;
               h2 = h2.next;
           }
       }else {
           n2 = n2-n1;
           while (n2-- >0 && h2 != null){
               h2 = h2.next;
           }
           while (h1 != null && h2 != null){
               if (h1 == h2){
                   return h1.data;
               }
               h1 = h1.next;
               h2 = h2.next;
           }
       }
        return -1;
    }
}