package tracker;

import org.junit.Test;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class SqlTrackerTest {
    public Connection init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void createItem() throws SQLException {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("name"));
            assertThat(tracker.findByName("name").size(), equalTo(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteItem() throws SQLException {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = new Item("name");
            tracker.add(item);
            tracker.delete(tracker.findByName(item.getName()).get(0).getId());
            assertThat(tracker.findByName("name").size(), equalTo(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAllItems() throws SQLException {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            List<Item> items = List.of(new Item("name1"), new Item("name2"), new Item("name3"), new Item("name4"));
            for (Item i : items) {
                tracker.add(i);
            }
            assertThat(tracker.findAll().size(), equalTo(4));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findByName() throws SQLException {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            List<Item> items = List.of(new Item("name1"), new Item("name1"), new Item("name3"), new Item("name4"));
            for (Item i : items) {
                tracker.add(i);
            }
            assertThat(tracker.findByName("name1").size(), equalTo(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findByIdTest() throws SQLException {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = new Item("name5");
            List<Item> items = List.of(new Item("name1"),item, new Item("name1"), new Item("name3"), new Item("name4"));
            for (Item i : items) {
                tracker.add(i);
            }
            String id = tracker.findByName(item.getName()).get(0).getId();
            assertThat(tracker.findById(id).getName(), equalTo(item.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
