class Solution {
    public List<Integer> findAnagrams(String s, String p) {
          int n = s.length();
          int m = p.length();
          int[] fre = new int[26];
          for (char c : p.toCharArray()){
              fre[c-'a']++ ;
          }
          //display(fre);
          List<Integer> output = new ArrayList<>();
          for (int i=0; i+m-1<n; i++){
              int[] dup = new int[26];
              for(int k = 0; k < 26; k++) {
                dup[k] = fre[k];
              }
              //System.out.println("Index: " + i);
              //display(dup);
              for (int j=i; j<i+m; j++){
                  if (dup[s.charAt(j)-'a']>0){
                      dup[s.charAt(j)-'a']-- ;
                  }
              }
              //display(dup);
              //System.out.println("============================================");
              boolean flag = true;
              for (int j=0; j<26; j++){
                  if (dup[j]!=0){
                      flag = false;
                      break;
                  }
              }
              if (flag){
                  output.add(i);
              }
          }
        return output;
    }

    void display(int f[]) {
        for(int i = 0; i < 26; i++) {
            int x = f[i];
            System.out.print(i + ": " + x + ", ");
        }
        System.out.println();
        System.out.println();
    }
}