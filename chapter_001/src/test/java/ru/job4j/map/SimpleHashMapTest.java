package ru.job4j.map;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleHashMapTest {

    @Test
    public void insertTest() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        String[] keys = new String[]{"key", "key2", "null", "AnoterKey_==!2%&31_'"};
        Integer[] values = new Integer[]{1, 2, 3, 4};

        //key with index 3 has same hash as key with index 3
        assertThat(map.insert(keys[0], values[0]), is(true));
        assertThat(map.insert(keys[1], values[1]), is(true));
        assertThat(map.insert(keys[2], values[2]), is(true));
        assertThat(map.insert(keys[3], values[3]), is(false));
    }

    @Test
    public void getTest() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        String[] keys = new String[]{"key", "key2", "null", "AnoterKey_==!2%&31_'"};
        Integer[] values = new Integer[]{1, 2, 3, 4};

        //key with index 3 has same hash as key with index 3
        for (int i = 0; i < keys.length; i++) {
            map.insert(keys[i], values[i]);
        }

        assertThat(map.get(keys[0]), is(values[0]));
        assertThat(map.get(keys[1]), is(values[1]));
        assertThat(map.get(keys[2]), is(values[2]));
        assertThat(map.get(keys[3]), is(values[2]));

    }

    @Test
    public void deleteTest() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        String[] keys = new String[]{"key", "key2", "null", "AnoterKey_==!2%&31_'"};
        Integer[] values = new Integer[]{1, 2, 3, 4};

        //key with index 3 has same hash as key with index 3
        for (int i = 0; i < keys.length; i++) {
            map.insert(keys[i], values[i]);
        }
        //key with index 3 has same hash as key with index 3
        assertThat(map.delete(keys[0]), is(true));
        assertThat(map.delete(keys[1]), is(true));
        assertThat(map.delete(keys[2]), is(true));
        assertThat(map.delete(keys[3]), is(false));

    }

    @Test
    public void doubleContainerSizeTest() {
        SimpleHashMap<Integer, Integer> map = new SimpleHashMap<>();
        for (int i = 0; i < 67; i++) {
            assertThat(map.insert(i, i), is(true));
        }
    }

    @Test
    public void iteratorsTest() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        String[] keys = new String[]{"key", "key2", "null", "AnoterKey_==!2%&31_'"};
        Integer[] values = new Integer[]{1, 2, 3, 4};
        //key with index 3 has same hash as key with index 3
        for (int i = 0; i < keys.length; i++) {
            map.insert(keys[i], values[i]);
        }
        Iterator<String> kIt = map.keysIterator();
        assertThat(kIt.next(), is(keys[1]));
        assertThat(kIt.next(), is(keys[2]));
        assertThat(kIt.next(), is(keys[0]));

    }
}
