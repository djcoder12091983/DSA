class Solution {
    public int mostWordsFound(String[] sentences) {
        int maxl = 0;
        for(String sentence : sentences) {
            String words[] = sentence.split(" ");
            int l = words.length;
            if(l > maxl) {
                maxl = l;
            }
        }
        
        return maxl;
    }
}