package onboarding;

import java.util.List;

class Problem1 {
    public static int solution(List<Integer> pobi, List<Integer> crong) {
        if (validate(pobi) || validate(crong)) return -1;

        int pobiMax = calculateMax(pobi);
        int crongMax = calculateMax(crong);

        return printResult(pobiMax, crongMax);
    }

    private static int printResult(int pobiMax, int crongMax) {
        if (pobiMax > crongMax) return 1;
        if (pobiMax < crongMax) return 2;
        return 0;
    }

    private static boolean validate(List<Integer> input) {
        if (input.size()!= 2) return false;
        for (int i = 0; i < input.size(); i++) {
            if (1 > input.get(i) || 400 < input.get(i)) {
                return true;
            }
        }
        if (input.get(0)%2 != 1 || input.get(1)%2 != 0) {
            return true;
        }
        if (input.get(0) + 1 != input.get(1)) {
            return true;
        }
        return false;
    }
    private static int calculateMax(List<Integer> input){
        int max = 0;
        for (int i = 0; i < input.size(); i++) {
            int plusResult = 0;
            int multiResult = 1;
            for (char digit : Integer.toString(input.get(i)).toCharArray()) {
                int tempDigit = Character.getNumericValue(digit);

                plusResult = plus(tempDigit, plusResult);
                multiResult = multi(tempDigit, multiResult);
            }
            if (max < plusResult) max = plusResult;
            if (max < multiResult) max = multiResult;
        }
        return max;
    }
    private static int plus(int digit, int result){
        return result += digit;
    }
    private static int multi(int digit, int result){
        return result *= digit;
    }
}