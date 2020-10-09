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

    @Test
    public void duplicates() {
        Analyze analyze = new Analyze();
        List<Analyze.User> previous = new ArrayList<>();
        List<Analyze.User> current = new ArrayList<>();
        int nrOfUsers = 2;
        int nrOfUsers2 = 6;
        addUsers(previous, nrOfUsers, add -> add < nrOfUsers, name -> false, 0);
        addUsers(previous, nrOfUsers2, add -> add < nrOfUsers2, name -> false, 0);
        addUsers(current, nrOfUsers, add -> add % 2 == 0, name -> name % 3 == 0, 0);
        addUsers(current, nrOfUsers2, add -> add % 2 == 0, name -> name % 3 == 0, 0);
        addNoise(previous, current);
        Analyze.Info info = analyze.diff(previous, current);
        assertThat(info.added(), equalTo(3));
        assertThat(info.deleted(), equalTo(3));
        assertThat(info.changed(), equalTo(2));
    }

    private void addNoise(List<Analyze.User> previous, List<Analyze.User> current) {
        Analyze.User zeroUser = new Analyze.User(0,"name0");
        Analyze.User zombieUser = new Analyze.User(99999,"zombie");
        Analyze.User zombieUser2 = new Analyze.User(99992,"zombie2");
        for(int i = 0; i < 10; i++){
            previous.add(zeroUser);
            current.add(zeroUser);
            previous.add(zombieUser);
            current.add(zombieUser);
            previous.add(zombieUser2);
            current.add(zombieUser2);
        }
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
