package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class MemStoreTest {
    private Base base1;
    private Base base2;
    private MemStore<Base> memStore;

    @Before
    public void init() {
        base1 = new User("1");
        base2 = new User("2");
        memStore = new MemStore<>();
    }

    @Test
    public void addTest() {
        memStore.add(base1);
        memStore.add(base2);
        assertThat(memStore.size(),is(2));
    }

    @Test
    public void replaceTest() {
        memStore.add(base1);
        memStore.replace(base1.getId(),base2);
        assertThat(memStore.findById(base2.getId()),is(base2));
    }

    @Test
    public void deleteTest() {
        memStore.add(base1);
        memStore.add(base2);
        memStore.delete(base2.getId());
        assertThat(memStore.findById(base2.getId()),is(nullValue()));
        assertThat(memStore.size(),is(1));

    }

    @Test
    public void findByIdTest() {
        memStore.add(base2);
        assertThat(memStore.findById(base2.getId()),is(base2));
        assertThat(memStore.findById(base1.getId()),is(nullValue()));
    }
}
