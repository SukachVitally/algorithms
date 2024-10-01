import java.util.*;

public class Test {

    public static void main(String[] args) {
        int a = 1;
        Integer b = 1;
        System.out.println(a == 1);
        System.out.println(b == 1);
        System.out.println(a == b);
        List<String> words = Arrays.asList("abaca", "cba", "abcd", "bcda", "dbac", "ebc");
        System.out.println(numberOfSimilarPairs(words));

    }

    public static int numberOfSimilarPairs(List<String> words) {
        Map<String, Integer> countMap = new HashMap<>();

        for (String word : words) {
            char[] chars = word.toCharArray();
            Set<Character> charSet = new TreeSet<>();
            for (char c : chars) {
                charSet.add(c);
            }

            String sortedWord = charSet.toString();

            countMap.put(sortedWord, countMap.getOrDefault(sortedWord, 0) + 1);
        }

        int count = 0;
        for (Integer value : countMap.values()) {
            count += value * (value - 1) / 2;
        }

        return count;
    }
}

// two strings are said to be similar if theay are composed of the same characters.
// For example "abaca" and "cba" are similar since both of them are composed of characters 'a', b' and 'c'.
// HOwever "abaca" and "bcd" are note similar since they do not chare all of the same letters.
// Given an array of strings words of length n, fid the nymber of pairs of strings that are similar