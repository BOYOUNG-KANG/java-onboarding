package onboarding;

import java.util.*;

public class Problem5 {
    public static List<Integer> solution(int money) {
        List<Integer> answer = new ArrayList<>();
        validate(money);
        Map<Integer, Integer> moneyDirectory = generateMoneyDirectory();

        int leftMoney = money;
        countMoney(money, answer, moneyDirectory, leftMoney);
        return answer;
    }

    private static Map<Integer, Integer> generateMoneyDirectory() {
        Map<Integer, Integer> moneyDirectory = new HashMap<>();
        moneyDirectory.putAll(Map.of(
                0, 50000,
                1, 10000,
                2, 5000,
                3, 1000,
                4, 500,
                5, 100,
                6, 50,
                7, 10,
                8, 1
        ));
        return moneyDirectory;
    }

    private static void countMoney(int money, List<Integer> answer, Map<Integer, Integer> moneyDirectory, int leftMoney) {
        for (int i = 0; i < moneyDirectory.size(); i++) {
            answer.add(i, leftMoney / moneyDirectory.get(i));
            leftMoney = money % moneyDirectory.get(i);
        }
    }

    private static void validate(int money){
        if (money < 1 || money > 1000000) throw new IllegalArgumentException();
    }
}
