class Solution {

    Map<Character, Integer> map = new HashMap<>();

    public Solution() {
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
    }

    public int romanToInt(String S) {
        int N = S.length();
        if(N == 0) {
            return 0;
        }
        char x = S.charAt(0);

        if(N == 1) {
            return map.get(x);
        }

        char next = S.charAt(1);
        if(x == 'I') {
            if(next == 'V') {
                return (map.get('V') - map.get('I')) + romanToInt(S.substring(2, N));
            } else if(next == 'X') {
                return (map.get('X') - map.get('I')) + romanToInt(S.substring(2, N));
            }
        } else if(x == 'X') {
            if(next == 'L') {
                return (map.get('L') - map.get('X')) + romanToInt(S.substring(2, N));
            } else if(next == 'C') {
                return (map.get('C') - map.get('X')) + romanToInt(S.substring(2, N));
            }
        } else if(x == 'C') {
            if(next == 'D') {
                return (map.get('D') - map.get('C')) + romanToInt(S.substring(2, N));
            } else if(next == 'M') {
                return (map.get('M') - map.get('C')) + romanToInt(S.substring(2, N));
            }
        }

        return map.get(x) + romanToInt(S.substring(1, N));
    }
}