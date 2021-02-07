package ru.job4j.ood.isp.menu.menu;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.ood.isp.menu.element.Header;
import ru.job4j.ood.isp.menu.element.MenuHeader;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MenuTest {
    public Menu menu;
    public HashMap<String, Header> allHeaders = new HashMap<>();

    @Test
    public void prettyPrintTest() {
        StringBuilder expected = new StringBuilder(
                ">1. First Chapter (id:1)\n" +
                        ">>1.2 Next chapter (id:2)\n" +
                        ">>>1.2.3 Next chapter (id:3)\n" +
                        ">>>>1.2.3.4 Next chapter (id:4)\n" +
                        ">>1.3 Next chapter (id:5)\n" +
                        ">2. New chapter (id:6)\n");

        String[] expArr = expected.toString().split("\n");
        String[] prettyStr = menu.prettyPrint().split("\n");
        assertThat(menu.prettyPrint().split("\n").length, is(expArr.length));

        for (int i = 0; i < prettyStr.length; i++) {
            String actual = prettyStr[i];
            String exp = expArr[i];
            System.out.println(String.format("exp: %s, actual: %s", exp, actual));
            assertThat(exp, is(actual));
        }
    }

    @Test
    public void getByIdTest() {
        for (int i = 0; i < 6; i++) {
            assertThat(allHeaders.get(i+""), is(menu.getById(i+"")));
        }
    }

    @Before
    public void prepareHeaders() {
        Header header = new MenuHeader("1. First Chapter", new DummyGenerator(1));
        Header chapter2 = new MenuHeader("1.2 Next chapter", new DummyGenerator(2));
        Header chapter3 = new MenuHeader("1.2.3 Next chapter", new DummyGenerator(3));
        Header chapter4 = new MenuHeader("1.2.3.4 Next chapter", new DummyGenerator(4));
        Header chapter13 = new MenuHeader("1.3 Next chapter", new DummyGenerator(5));
        Header newChapter2 = new MenuHeader("2. New chapter", new DummyGenerator(6));
        header.add(chapter2);
        chapter2.add(chapter3);
        chapter3.add(chapter4);
        header.add(chapter13);
        menu = new Menu(header);
        menu.add(newChapter2);

        allHeaders.put(header.getID(), header);
        allHeaders.put(chapter2.getID(), chapter2);
        allHeaders.put(chapter3.getID(), chapter3);
        allHeaders.put(chapter4.getID(), chapter4);
        allHeaders.put(chapter13.getID(), chapter13);
        allHeaders.put(newChapter2.getID(), newChapter2);
    }

}