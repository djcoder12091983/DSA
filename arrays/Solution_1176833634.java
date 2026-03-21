class Solution {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int h = 0;
        int N = citations.length;
        for(int i = N - 1; i >= 0; i--) {
            if(citations[i] >= N - i) {
                h = N - i;
            }
        }
        return h;
    }
}