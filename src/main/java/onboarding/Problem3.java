package onboarding;

public class Problem3 {
    public static int solution(int number) {
        validate(number);
        return calculateTotalClap(number);
    }

    private static int calculateTotalClap(int number) {
        int totalCount = 0;
        for (int nowNumber = 1; nowNumber < number + 1; nowNumber ++) {
            totalCount += calculateClap(nowNumber);
        }
        return totalCount;
    }

    private static void validate(int number){
        if (number < 1 || number > 10000) throw new IllegalArgumentException();
    }
    private static int calculateClap(int nowNumber){
        int count = 0;
        String temp = String.valueOf(nowNumber);
        for (char now : temp.toCharArray()) {
            count += updateCount(now);
        }
        return count;
    }

    private static int updateCount(char now) {
        int temp = 0;
        if (now == '3' || now == '6' || now == '9') temp = 1;
        return temp;
    }
}
