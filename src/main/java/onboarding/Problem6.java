package onboarding;

import java.util.*;
import java.util.stream.Collectors;

public class Problem6 {
    public static List<String> solution(List<List<String>> forms) {
        validate(forms);

        HashMap<String, Integer> slicedNickname = new HashMap<>();
        for (int i = 0; i < forms.size(); i++) {
            String nickname = forms.get(i).get(1);
            duplicatedChecker(slicedNickname, nickname);
        }
        List<String> duplicatedEmail = printDuplicatedEmail(forms, slicedNickname);
        return duplicatedEmail;
    }

    private static void duplicatedChecker(HashMap<String, Integer> slicedNickname, String nickname) {
        for (int j = 0; j < nickname.length() - 1; j++){
            String duplicatedNickname = nickname.substring(j, j + 1) + nickname.substring(j + 1, j + 2);
            updateDuplicatedNickname(slicedNickname, duplicatedNickname);
        }
    }

    private static List<String> printDuplicatedEmail(List<List<String>> forms, HashMap<String, Integer> slicedNickname) {
        Map<String, Integer> duplicatedResult = slicedNickname.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        Set<String> answer = new HashSet<>();
        for (String key : duplicatedResult.keySet()) {
            containDuplicatedNickname(forms, answer, key);
        }
        return new ArrayList<>(answer);
    }

    private static void containDuplicatedNickname(List<List<String>> forms, Set<String> answer, String key) {
        for (int i = 0; i < forms.size(); i++) {
            if (forms.get(i).get(1).contains(key)) {
                answer.add(forms.get(i).get(0));
            }
        }
    }

    private static void updateDuplicatedNickname(HashMap<String, Integer> slicedNickname, String duplicatedNickname) {
        if (slicedNickname.get(duplicatedNickname) != null) {
            Integer temp = slicedNickname.get(duplicatedNickname);
            slicedNickname.put(duplicatedNickname, temp + 1);
        }
        if (slicedNickname.get(duplicatedNickname) == null) {
            slicedNickname.put(duplicatedNickname, 1);
        }
    }

    private static void validate(List<List<String>> forms) {
        if (forms.size() < 1 || forms.size() > 10000) throw new IllegalArgumentException();

        for (int i = 0; i < forms.size(); i ++) {
            String email = forms.get(i).get(0);
            String nickname = forms.get(i).get(1);
            if (!email.contains("@email.com")) throw new IllegalArgumentException();
            if (email.length() < 11 || email.length() > 19) throw new IllegalArgumentException();
            if (!nickname.matches("^[가-힣]*$")) throw new IllegalArgumentException();
            if (nickname.length() < 1 || nickname.length() > 19) throw new IllegalArgumentException();
        }
    }
}
