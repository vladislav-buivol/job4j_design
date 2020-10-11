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
            HashMap<Integer, User> currentUserEntries = createUserEntries(current);
            findNrOfChangedAndDeletedUsers(currentUserEntries);
            findNumberOfAddedUsers();
        }

        private void findNrOfChangedAndDeletedUsers(HashMap<Integer, User> currentUserEntries) {
            for (User pUser : previous) {
                int id = pUser.id();
                if (currentUserEntries.containsKey(id)) {
                    if (!currentUserEntries.get(id).name().equals(pUser.name())) {
                        changed++;
                    }
                } else {
                    deleted++;
                }
            }
        }

        private void findNumberOfAddedUsers() {
            for (User cUser : current) {
                if (!previous.contains(cUser)) {
                    added++;
                }
            }
        }

        private HashMap<Integer, User> createUserEntries(List<User> users) {
            HashMap<Integer, User> entries = new HashMap<>();
            for (User user : users) {
                entries.put(user.id(), user);
            }
            return entries;
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