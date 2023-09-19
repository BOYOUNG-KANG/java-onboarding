package onboarding;

public class Problem2 {
    public static String solution(String cryptogram) {
        StringBuilder answer = new StringBuilder(cryptogram);
        validate(cryptogram);
        do {
            answer = removeDuplicateChars(answer);
        } while (checkDuplicateChars(answer));

        return answer.toString();
    }
    private static void validate(String cryptogram){
        if (cryptogram.length() < 1 || cryptogram.length() > 1000) throw new IllegalArgumentException();
        if (cryptogram.toLowerCase() != cryptogram) throw new IllegalArgumentException();
    }
    private static StringBuilder removeDuplicateChars(StringBuilder cryptogram){
        for (int i = 0; i < cryptogram.length()-1; i++) {
            i = deleteDuplicateChars(cryptogram, i);
        }
        return cryptogram;
    }

    private static int deleteDuplicateChars(StringBuilder cryptogram, int i) {
        char now = cryptogram.charAt(i);
        char next = cryptogram.charAt(i + 1);
        if (now == next) {
            cryptogram.delete(i, i + 2);
            i -= 1;
        }
        return i;
    }

    private static boolean checkDuplicateChars(StringBuilder cryptogram){
        for (int i = 0; i < cryptogram.length()-1; i++) {
            char now = cryptogram.charAt(i);
            char next = cryptogram.charAt(i+1);
            if (now == next) return true;
        }
        return false;
    }


}
