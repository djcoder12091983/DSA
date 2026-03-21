class Solution {
    public int findMin(int[] A) {
        int l = 0, h = A.length - 1;
        //int c = 0;
        while(l < h) {
            if(A[l] < A[h]) {
                return A[l];
            }
            int mid = (l + h) / 2;
            if(mid - 1 >= 0 && A[mid] < A[mid - 1]) {
                return A[mid];
            }
            
            //System.out.println(l + " " + h + " " + mid);
            //boolean move = false;
            if(l < mid && A[mid] < A[l]) {
                // go to left side
                h = mid - 1;
                //move = true;
            } else if(h > mid && A[mid] > A[h]) {
                // go right side
                l = mid + 1;
                //move = true;
            } 

            /*if(move == false) {
                break; // l is my answer
            }*/
        }

        return A[l];
    }
}