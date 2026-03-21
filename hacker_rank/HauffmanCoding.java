/*  
	class Node
		public  int frequency; // the frequency of this tree
    	public  char data;
    	public  Node left, right;
    
*/ 

void decode(String S ,Node root)
{
        
    int l = S.length();
    // try to get best-match
    Node node = root;
    int i = 0;
    StringBuilder text = new StringBuilder();
    while(i<l) {
        char ch = S.charAt(i);
        if(ch == '0') {
            node = node.left;
        } else if(ch == '1') {
            node = node.right;
        }
        if(node.data != '\0') {
            // leaf node
            // reset
            text.append(node.data);
            node = root;
        }
        i++;
    }
    System.out.println(text);
}