class FreqStack {
  public HashMap<Integer,Stack<Integer>> map = new HashMap<>();
  public HashMap<Integer,Integer> fmap = new HashMap<>();

    public FreqStack() {
    }
    public void push(int val) {
      int f=fmap.getOrDefault(val,0)+1;
      if(map.containsKey(f)==false){
        map.put(f,new Stack<>());
      }
      map.get(f).add(val);
     fmap.put(val,f);
     /*System.out.println("PUSH:");
     System.out.println(map);
     System.out.println(fmap);
     System.out.println();*/
    }
    public int pop() {
        int maxfreq=map.size();
        int maxfval=(map.get(maxfreq).pop());
        if(map.get(maxfreq).size()==0){
          map.remove(maxfreq);
        }
        fmap.put(maxfval,fmap.get(maxfval)-1);
        if(fmap.get(maxfval) == 0) {
            fmap.remove(maxfval);
        }
        /*System.out.println("POP:");
        System.out.println(map);
        System.out.println(fmap);
        System.out.println();*/
        return maxfval;
    }
}

 //[[],[5],[7],[5],[7],[4],[5],[],[],[],[]]

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */