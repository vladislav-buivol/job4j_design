package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("name"),
                equalTo("Petr Arsentev")
        );
    }

    @Test
    public void whenWithCommentAndEmptyLines() {
        String path = "./data/pair_withCommentsAndEmptyLines_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("hibernate.dialect"),
                equalTo("org.hibernate.dialect.PostgreSQLDialect")
        );
        assertThat(
                config.value("hibernate.connection.driver_class"),
                equalTo("org.postgresql.Driver")
        );
        assertThat(
                config.value("hibernate.connection.password"),
                equalTo("password")
        );
        assertThat(
                config.value("commented.connection.password"),
                equalTo(null)
        );
        assertThat(
                config.value("comments"),
                equalTo(null)
        );
    }
}
