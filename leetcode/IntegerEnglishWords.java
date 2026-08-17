// https://leetcode.com/problems/integer-to-english-words

class Solution {

    // words dictionary
    String dict1[] = new String[]{"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    String dict2[] = new String[]{"Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    String dict3[] = new String[]{"Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

    // handle hundred unit
    String unit(int N) {
        String words = "";
        int rem = N % 100;
        int x = rem % 10;
        if(rem >= 10) {
            int y = rem / 10;
            if(x == 0) {
                words += dict2[y - 1];
            } else if(rem < 20) {
                words += dict3[x - 1];
            } else {
                // normal
                words += dict2[y - 1] + " " + dict1[x - 1];
            }
        } else {
            if(x > 0) {
                words += dict1[x - 1];
            }
        }

        int z = N / 100;
        if(z > 0) {
            words = dict1[z - 1] + " Hundred " + words;
        }

        return words.trim(); // remove space
    }

    public String numberToWords(int N) {

        if(N == 0) {
            return "Zero"; // edge case
        }

        // will split 3 size window (hundred - then thousand - then million - then rest billion)
        String words = "";
        // hundred
        int rem = N % 1000;
        words += unit(rem);
        // thousand
        rem = (N / 1000) % 1000;
        if(rem > 0) {
            words = unit(rem) + " Thousand " + words;
        }
        // million
        rem = (N / 1000000) % 1000;
        if(rem > 0) {
            words = unit(rem) + " Million " + words;
        }
        // billion
        rem = (N / 1000000000) % 1000;
        if(rem > 0) {
            words = unit(rem) + " Billion " + words;
        }

        return words.trim(); // remove space
    }
}