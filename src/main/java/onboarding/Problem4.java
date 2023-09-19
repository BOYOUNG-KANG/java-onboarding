package onboarding;

import java.util.HashMap;
import java.util.Map;

public class Problem4 {
    public static String solution(String word) {
        validate(word);
        return convert(word);
    }
    private static void validate(String word) {
        if (word.length() < 1 || word.length() > 1000) throw new IllegalArgumentException();
    }
    private static String convert(String word) {
        Map<String, String> directory = alphabetDirectory();

        StringBuilder convertedWord = new StringBuilder();
        for(char digit : word.toCharArray()) {
            String alphabet = String.valueOf(digit);
            boolean isUpperCase = alphabet == alphabet.toUpperCase() && !alphabet.contains(" ");
            boolean isLowerCase = alphabet == alphabet.toLowerCase() && !alphabet.contains(" ");
            convertDigit(directory, convertedWord, alphabet, isUpperCase, isLowerCase);
        }
        return convertedWord.toString();
    }

    private static void convertDigit(Map<String, String> directory, StringBuilder convertedWord, String alphabet, boolean isUpperCase, boolean isLowerCase) {
        if (alphabet.contains(" ")) {
            convertedWord.append(alphabet);}
        if(isUpperCase) {
            convertToUpperCase(directory, convertedWord, alphabet);
        }
        if (isLowerCase) {
            convertToLowerCase(directory, convertedWord, alphabet);
        }
    }

    private static void convertToLowerCase(Map<String, String> directory, StringBuilder convertedWord, String alphabet) {
        convertedWord.append(directory.get(alphabet.toUpperCase()).toLowerCase());
    }

    private static void convertToUpperCase(Map<String, String> directory, StringBuilder convertedWord, String alphabet) {
        convertedWord.append(directory.get(alphabet));
    }

    private static Map<String, String> alphabetDirectory() {
        Map<String, String> directory = new HashMap<>();
        directory.putAll(Map.of(
                "A", "Z",
                "B", "Y",
                "C", "X",
                "D", "W",
                "E", "V",
                "F", "U",
                "G", "T",
                "H", "S",
                "I", "R",
                "J", "Q"
        ));
        directory.putAll(Map.of(
                "K", "P",
                "L", "O",
                "M", "N",
                "N", "M",
                "O", "L",
                "P", "K",
                "Q", "J",
                "R", "I",
                "S", "H",
                "T", "G"
        ));
        directory.putAll(Map.of(
                "U", "F",
                "V", "E",
                "W", "D",
                "X", "C",
                "Y", "B",
                "Z", "A"
        ));
        return directory;
    }
}
