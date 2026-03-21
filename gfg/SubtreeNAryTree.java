

//User function Template for Java


class Solution{
    
    boolean compare(Node T1, Node T2) {
        if(T1 == null && T2 == null) {
            // both reach leaf node
            return true;
        }
        if(T1 == null || T2 == null) {
            // if one reach leaf than other
            return false;
        }
        
        if(T1.data == T2.data) {
            // data match then look for other subtree
            List<Node> t1children = T1.children;
            List<Node> t2children = T2.children;
            int N1 = t1children.size();
            int N2 = t2children.size();
            
            if(N1 == N2) {
                // size same then we can check subtree structure
                for(int i = 0; i < N1; i++) {
                    if(!compare(t1children.get(i), t2children.get(i))) {
                        // not same, straight way return false
                        return false;
                    }
                }
                // all are same
                return true;
            } else {
                return false; // structure not same
            }

        } else {
            return false; // structure seems to be same but value does not match
        }
    }
    
    // tree traversal to collect all nodes
    void collect(Node node, ArrayList<Node> nodes) {
        if(node == null) {
            return;
        }
        nodes.add(node);
        for(Node child : node.children) {
            collect(child, nodes);
        }
    }
    
    public int duplicateSubtreeNaryTree(Node root){
        // tree traversal to collect all nodes
        ArrayList<Node> nodes = new ArrayList<>();
        collect(root, nodes);
        
        // now check for duplicates
        int N = nodes.size();
        int duplicates = 0;
        boolean processed[] = new boolean[N + 1];
        for(int i = 0; i < N; i++) {
            if(processed[i] == true) {
                continue; // no need to process it again
            }
            processed[i] = true;
            boolean duplicate = false;
            for(int j = i + 1; j < N; j++) {
                
                if(processed[j] == true) {
                    continue; // no need to process it again
                }
                
                if(compare(nodes.get(i), nodes.get(j))) {
                    // found duplicate
                    duplicate = true;
                    processed[j] = true; // mark it as processed
                }
            }
            
            if(duplicate) {
                duplicates++;
            }
        }
        
        return duplicates;
    }
    
}