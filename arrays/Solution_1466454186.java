/*
// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    
    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}
*/

class Solution {
    public Node construct(int[][] G) {
        return constructQT(G, 0, 0, G.length, G[0].length);
    }

    Node constructQT(int[][] G, int top, int left, int bottom, int right) {
        //System.out.println("[" + top + " " + left + "], [" + bottom + " " + right + "]");
        if(bottom - top == 1) {
            // leaf
            return new Node(G[top][left] == 1, true);
        }

        // if all values are either 1 or 0
        int c0 = 0, c1 = 0;
        for(int i = top; i < bottom; i++) {
            for(int j = left; j < right; j++) {
                if(G[i][j] == 1) {
                    c1++;
                } else {
                    c0++;
                }
            }
        }

        if(c0 == 0) {
            // all are 1, leaf 1
            return new Node(true, true);
        } else if(c1 == 0) {
            // all are 0, leaf 0
            return new Node(false, true);
        }

        // split into four halves
        Node root = new Node(true, false);

        // recursively call for four quadrants
        int halfx = (bottom + top)/2, halfy = (right + left)/2;
        root.topLeft = constructQT(G, top, left, halfx, halfy); // top left
        root.topRight = constructQT(G, top, halfy, halfx, right); // top right
        root.bottomLeft = constructQT(G, halfx, left, bottom, halfy); // bottom left
        root.bottomRight = constructQT(G, halfx, halfy, bottom, right); // bottom right

        return root;

    }
}