package ru.job4j.collection;

import java.util.*;

public class Analyze {

    public Info diff(List<User> previous, List<User> current) {
        return new Info(previous, current);
    }

    public static class User {
        private final int id;
        private final String name;

        public int id() {
            return id;
        }

        public String name() {
            return name;
        }

        public User(int id, String name) {
            this.id = id;
            this.name = name;

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof User)) return false;
            User user = (User) o;
            return id == user.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static class Info {
        private int added = 0;
        private int changed = 0;
        private int deleted = 0;
        private final List<User> previous;
        private final List<User> current;

        public Info(List<User> previous, List<User> current) {
            this.previous = previous;
            this.current = current;
            diff();
        }

        private void diff() {
            HashMap<User, Integer> cUsers = countUsers(current);
            HashMap<User, Integer> difference = findChangedAndDeletedUsers(previous, cUsers);
            findAddedUsers(difference);
        }

        private HashMap<User, Integer> findChangedAndDeletedUsers(List<User> comparableUsers, HashMap<User, Integer> cUsers) {
            HashMap<User, Integer> result = (HashMap) cUsers.clone();
            mainLoop:
            for (User pUser : comparableUsers) {
                for (Map.Entry<User, Integer> entry : result.entrySet()) {
                    if (isNameAndIdEquals(entry, pUser)) {
                        int nrOfUsers = getNrOfUsers(result, entry.getKey());
                        decreaseQuantityByOne(result, entry, nrOfUsers);
                        continue mainLoop;
                    } else if (IsIdEqualsAndNamesDifferent(pUser, entry)) {
                        int nrOfUsers = getNrOfUsers(result, entry.getKey());
                        decreaseQuantityByOne(result, entry, nrOfUsers);
                        if (nrOfUsers > 0) {
                            changed++;
                        }
                        continue mainLoop;
                    }
                }
                deleted++;
            }
            return result;
        }

        private void findAddedUsers(HashMap<User, Integer> cUsers) {
            for (User user : cUsers.keySet()) {
                added = added + getNrOfUsers(cUsers, user);
            }
        }

        private HashMap<User, Integer> countUsers(List<User> users) {
            HashMap<User, Integer> res = new HashMap<>();
            for (User user : users) {
                res.merge(user, 1, Integer::sum);
            }
            return res;
        }

        boolean isNameAndIdEquals(Map.Entry<User, Integer> entry, User pUser) {
            return entry.getKey().equals(pUser) && entry.getKey().name().equals(pUser.name());
        }

        private Integer getNrOfUsers(HashMap<User, Integer> cUsers, User key) {
            return cUsers.get(key);
        }

        private void decreaseQuantityByOne(HashMap<User, Integer> cUsers, Map.Entry<User, Integer> entry, int nrOfUsers) {
            cUsers.put(entry.getKey(), nrOfUsers - 1);
        }

        private boolean IsIdEqualsAndNamesDifferent(User pUser, Map.Entry<User, Integer> entry) {
            return entry.getKey().equals(pUser) && !entry.getKey().name().equals(pUser.name());
        }

        public int added() {
            return added;
        }

        public int changed() {
            return changed;
        }

        public int deleted() {
            return deleted;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "added=" + added +
                    ", changed=" + changed +
                    ", deleted=" + deleted +
                    '}';
        }
    }
}