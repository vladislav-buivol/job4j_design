package ru.job4j.template;

import org.junit.Test;
import ru.job4j.template.exception.ExtraKeyFoundException;
import ru.job4j.template.exception.KeyNotFoundException;

import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GeneratorTest {

    @Test
    public void replaceKeyTest() {
        MessageGenerator generator = new MessageGenerator();
        HashMap<String, String> keys1 = new HashMap<>();
        keys1.put("name", "Petr");
        keys1.put("subject", "you");
        String meetMessageTemplate1 = "I am a ${name}, Who are ${subject}?";
        String result = generator.produce(meetMessageTemplate1, keys1);
        assertThat(result, is("I am a Petr, Who are you?"));
    }

    @Test
    public void replaceFirstAndLastTest() {
        MessageGenerator generator = new MessageGenerator();
        String meetMessageTemplate2 = "I am a ${name}";
        HashMap<String, String> keys2 = new HashMap<>();
        keys2.put("name", "Petr");
        String result2 = generator.produce(meetMessageTemplate2, keys2);
        assertThat(result2, is("I am a Petr"));
        meetMessageTemplate2 = "${name} some text";
        result2 = generator.produce(meetMessageTemplate2, keys2);
        assertThat(result2, is("I am a Petr"));
    }

    @Test
    public void replaceOneKeyTest() {
        MessageGenerator generator = new MessageGenerator();
        String meetMessageTemplate3 = "${name}";
        HashMap<String, String> keys3 = new HashMap<>();
        keys3.put("name", "Petr");
        String result3 = generator.produce(meetMessageTemplate3, keys3);
        assertThat(result3, is("Petr"));
    }

    @Test(expected = KeyNotFoundException.class)
    public void keyNotFoundException() {
        MessageGenerator generator = new MessageGenerator();
        HashMap<String, String> keys = new HashMap<>();
        keys.put("name", "Petr");
        keys.put("keyWithType", "you");
        String meetMessageTemplate = "I am a ${name}, Who are ${subject}?";
        String result = generator.produce(meetMessageTemplate, keys);
    }

    @Test(expected = KeyNotFoundException.class)
    public void templateWithoutKey() {
        MessageGenerator generator = new MessageGenerator();
        HashMap<String, String> keys = new HashMap<>();
        keys.put("name", "Petr");
        keys.put("subject", "you");
        String meetMessageTemplate = "I am a {name}, Who are ${subject}?";
        String result = generator.produce(meetMessageTemplate, keys);
    }

    @Test(expected = KeyNotFoundException.class)
    public void templateWithWrongKey() {
        MessageGenerator generator = new MessageGenerator();
        HashMap<String, String> keys = new HashMap<>();
        keys.put("name", "Petr");
        keys.put("subject", "you");
        String meetMessageTemplate = "I am a $name, Who are ${subject}?";
        String result = generator.produce(meetMessageTemplate, keys);
    }

    @Test(expected = ExtraKeyFoundException.class)
    public void extraKeyFoundException() {
        MessageGenerator generator = new MessageGenerator();
        HashMap<String, String> keys = new HashMap<>();
        keys.put("name", "Petr");
        keys.put("subject", "you");
        keys.put("someExtraKey", "you");
        String meetMessageTemplate = "I am a ${name}, Who are ${subject}?";
        String result = generator.produce(meetMessageTemplate, keys);
    }
}