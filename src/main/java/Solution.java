import java.util.ArrayList;
import java.util.List;

class Solution {
    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        KMP kmp = new KMP(goal);
        return !kmp.KMPSearch(s + s).isEmpty();


    }
}

class KMP {
    int[] lps;
    String pattern;
    int m;

    public KMP(String pattern) {
        this.pattern = pattern;
        m = pattern.length();
        lps = new int[m];
        computeLPSArray();

    }

    public List<Integer> KMPSearch(String text) {
        List<Integer> matches = new ArrayList<>();
        int n = text.length();

        int j = 0;
        int i = 0;

        while (n - i >= m - j) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }
            if (j == m) {
                matches.add(i - j);
                j = lps[j - 1];
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i = i + 1;
                }
            }
        }

        return matches;

    }

    private void computeLPSArray() {
        int length = 0;
        int i = 1;
        lps[0] = 0;

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            } else {
                if (length != 0) {
                    length = lps[length - 1];
                } else {
                    lps[i] = length;
                    i++;
                }
            }
        }
    }
}