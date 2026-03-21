class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<Integer>());
        list.get(0).add(1);
        for(int i = 1 ; i <= rowIndex ; i++){
            List<Integer> curr = new ArrayList<>();
            curr.add(1);
            for(int j = 1 ; j < i ; j++){
                curr.add(list.get(i-1).get(j-1) + list.get(i-1).get(j));
            }
            curr.add(1);
            list.add(curr);
        }
        return list.get(rowIndex);
    }
}