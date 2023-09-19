package onboarding;

import java.util.*;
import java.util.stream.Collectors;

public class Problem7 {
    public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        validateUser(user);
        validateFriends(friends);
        validateVisitor(visitors);

        Map<String, Integer> recommendedFriends = new HashMap<>();
        List<String> originFriends = makeOriginFriendsList(user, friends);

        for (int i = 0; i < friends.size(); i++) {
            calculateSharedFriends(user, friends, recommendedFriends, originFriends, i);
        }
        for (int i = 0; i < visitors.size(); i++) {
            String visitor = visitors.get(i);
            calculateVisitors(recommendedFriends, originFriends, visitor);
        }
        List<String> answer = printRecommendFriends(recommendedFriends);
        return answer;
    }

    private static List<String> printRecommendFriends(Map<String, Integer> recommendedFriends) {
        Map<String, Integer> selectedFriends = recommendedFriends.entrySet()
                .stream()
                .filter(friend -> friend.getValue() > 0)
                .limit(5)
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        List<String> answer = new ArrayList<>();
        for (String friend : selectedFriends.keySet()) {
            answer.add(friend);
        }
        return answer;
    }

    private static void calculateVisitors(Map<String, Integer> recommendedFriends, List<String> originFrinds, String visitor) {
        if (!originFrinds.contains(visitor)) {
            addVisitorScore(recommendedFriends, visitor);
        }
    }

    private static void addVisitorScore(Map<String, Integer> recommendedFriends, String visitor) {
        if (recommendedFriends.get(visitor) != null) {
            Integer nowScore = recommendedFriends.get(visitor);
            recommendedFriends.put(visitor, nowScore + 1);
        }
        if (recommendedFriends.get(visitor) == null) {
            recommendedFriends.put(visitor, 1);
        }
    }

    private static void calculateSharedFriends(String user, List<List<String>> friends, Map<String, Integer> recommendedFriends, List<String> originFriends, int i) {
        for (int originFriend = 0; originFriend < originFriends.size(); originFriend++) {
            containsSharedFriends(user, friends, recommendedFriends, originFriends, i, originFriend);
        }
    }

    private static void containsSharedFriends(String user, List<List<String>> friends, Map<String, Integer> recommendedFriends, List<String> originFriends, int i, int originFriend) {
        if (friends.get(i).get(0).equals(originFriends.get(originFriend)) && !friends.get(i).get(1).equals(user)) {
            String recommendFriend = friends.get(i).get(1);
            addSharedFriendScore(recommendedFriends, recommendFriend);
        }
        if (friends.get(i).get(1).equals(originFriends.get(originFriend)) && !friends.get(i).get(0).equals(user)) {
            String recommendFriend = friends.get(i).get(0);
            addSharedFriendScore(recommendedFriends, recommendFriend);
        }
    }

    private static void addSharedFriendScore(Map<String, Integer> recommendedFriends, String recommendFriend) {
        if (recommendedFriends.get(recommendFriend) != null) {
            Integer nowScore = recommendedFriends.get(recommendFriend);
            recommendedFriends.put(recommendFriend, nowScore + 10);
        }
        if (recommendedFriends.get(recommendFriend) == null) {
            recommendedFriends.put(recommendFriend, 10);
        }
    }

    private static List<String> makeOriginFriendsList(String user, List<List<String>> friends) {
        List<String> originFriends = new ArrayList<>();
        for (int i = 0; i < friends.size(); i++) {
            updateOriginFriends(user, friends, originFriends, i);
        }
        return originFriends;
    }

    private static void updateOriginFriends(String user, List<List<String>> friends, List<String> originFriends, int i) {
        if (friends.get(i).get(0).contains(user)) {
            originFriends.add(0, friends.get(i).get(1));
        }
        if (friends.get(i).get(1).contains(user)) {
            originFriends.add(0, friends.get(i).get(0));
        }
    }

    private static void validateUser(String user) {
        if (user.length() < 1 || user.length() > 30) throw new IllegalArgumentException();
    }
    private static void validateFriends(List<List<String>> friends) {
        if (friends.size() < 1 || friends.size() > 10000) throw new IllegalArgumentException();
        for (int i = 0; i < friends.size(); i ++) {
            List<String> friend = friends.get(i);
            validateFriend(friend);
        }
    }

    private static void validateFriend(List<String> friend) {
        if (friend.size() != 2) throw new IllegalArgumentException();
        if (friend.get(0).length() < 1 || friend.get(0).length() > 30) throw new IllegalArgumentException();
        if (friend.get(1).length() < 1 || friend.get(1).length() > 30) throw new IllegalArgumentException();
    }

    private static void validateVisitor(List<String> visitors) {
        if (visitors.size() < 0 || visitors.size() > 10000) throw new IllegalArgumentException();
    }
}