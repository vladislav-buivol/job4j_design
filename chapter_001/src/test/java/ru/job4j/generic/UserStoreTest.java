package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class UserStoreTest {
    private User base1;
    private User base2;
    private UserStore userStore;

    @Before
    public void init() {
        base1 = new User("1");
        base2 = new User("2");
        userStore = new UserStore();
    }

    @Test
    public void addTest() {
        userStore.add(base1);
        userStore.add(base2);
        assertThat(userStore.size(),is(2));
    }

    @Test
    public void replaceTest() {
        userStore.add(base1);
        userStore.replace(base1.getId(),base2);
        assertThat(userStore.findById(base2.getId()),is(base2));
    }

    @Test
    public void deleteTest() {
        userStore.add(base1);
        userStore.add(base2);
        userStore.delete(base2.getId());
        assertThat(userStore.findById(base2.getId()),is(nullValue()));
        assertThat(userStore.size(),is(1));

    }

    @Test
    public void findByIdTest() {
        userStore.add(base2);
        assertThat(userStore.findById(base2.getId()),is(base2));
        assertThat(userStore.findById(base1.getId()),is(nullValue()));
    }
}
