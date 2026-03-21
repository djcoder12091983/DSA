class Solution {
    List<List<Integer>> list = new ArrayList<List<Integer>>();
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        int N = groupSizes.length;
        ArrayList<Integer>[] arr = new ArrayList[N+1];
        for(int j = 0; j <= N; j++) {
            arr[j] = new ArrayList<>();
        }
        //Arrays.fill(arr, new ArrayList<>());
        for(int i=0; i<N; i++){
            int index = groupSizes[i];
            List<Integer> temp = arr[index];
            /*for(int j = 0; j <= N; j++) {
                System.out.println(j + " => " + arr[j]);
            }
            System.out.println();*/
            if(temp.size()<index){
                temp.add(i);
            }
            else {
                // System.out.println(i);
                list.add(new ArrayList<>(temp));
                //System.out.println(list);
                arr[index] = new ArrayList<>();
                arr[index].add(i);
            }
        }
        for(int i=0; i<arr.length; i++){
            if(arr[i].size()>0){
                // System.out.println("i");
                list.add(new ArrayList<>(arr[i]));
            }
        }
        return list;
    }
}