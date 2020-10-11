package ru.job4j.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AnalyzeTest {

    @Test
    public void notChanged() {
        Analyze analyze = new Analyze();
        List<Analyze.User> previous = new ArrayList<>();
        List<Analyze.User> current = new ArrayList<>();
        int nrOfUsers = 100;
        addUsers(previous, nrOfUsers, add -> add < nrOfUsers, name -> false, 0);
        addUsers(current, nrOfUsers, add -> add < nrOfUsers, name -> false, 0);
        Collections.shuffle(current);
        Analyze.Info info = analyze.diff(previous, current);
        assertThat(info.added(), equalTo(0));
        assertThat(info.deleted(), equalTo(0));
        assertThat(info.changed(), equalTo(0));
    }

    @Test
    public void addAndChangeDelete() {
        Analyze analyze = new Analyze();
        List<Analyze.User> previous = new ArrayList<>();
        List<Analyze.User> current = new ArrayList<>();
        int nrOfUsers = 10;
        addUsers(previous, nrOfUsers, add -> add < nrOfUsers, name -> false, 0);
        addUsers(current, nrOfUsers, add -> add % 2 == 0, name -> name % 3 == 0, 0);
        Analyze.User userX = new Analyze.User(1000, "userX");
        current.add(userX);
        Analyze.Info info = analyze.diff(previous, current);
        assertThat(info.added(), equalTo(6));
        assertThat(info.deleted(), equalTo(5));
        assertThat(info.changed(), equalTo(2));
    }

    @Test
    public void delete() {
        Analyze analyze = new Analyze();
        List<Analyze.User> previous = new ArrayList<>();
        List<Analyze.User> current = new ArrayList<>();
        int nrOfUsers = 8;
        addUsers(previous, nrOfUsers, add -> add < nrOfUsers, name -> false, 0);
        addUsers(current, nrOfUsers, add -> true, name -> false, nrOfUsers / 2);
        Analyze.Info info = analyze.diff(previous, current);
        assertThat(info.added(), equalTo(4));
        assertThat(info.deleted(), equalTo(4));
        assertThat(info.changed(), equalTo(0));
    }

    private void addUsers(List<Analyze.User> users, int nrOfUsers, Predicate<Integer> addCondition, Predicate<Integer> nameCondition, int startFrom) {
        String name;
        while (users.size() < nrOfUsers) {
            if (addCondition.test(startFrom)) {
                if (nameCondition.test(startFrom)) {
                    name = "changedName" + startFrom;
                } else {
                    name = "name" + startFrom;
                }
                users.add(new Analyze.User(startFrom, name));
            }
            startFrom++;
        }
    }
}
