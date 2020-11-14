package tracker;

import propertyReader.DatabaseProperties;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


public class SqlTracker implements Store {
    private Connection cn;
    private String itemsTable = "items";

    public static void main(String[] args) {
        Input validate = new ValidateInput(
                new ConsoleInput()
        );
        try (Store tracker = new SqlTracker()) {
            tracker.init();
            List<UserAction> actions = new ArrayList<>();
            actions.add(new CreateAction());
            new StartUI().init(validate, tracker, actions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void init() {
        try {
            DatabaseProperties.readProperties();
            Class.forName(DatabaseProperties.driver());
            cn = DriverManager.getConnection(
                    DatabaseProperties.url(),
                    DatabaseProperties.login(),
                    DatabaseProperties.password()
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Item add(Item item) {
        try {
            String addQuery = String.format("INSERT INTO %s(name) VALUES(?)", itemsTable);
            PreparedStatement stmt = cn.prepareStatement(addQuery);
            stmt.setString(1, item.getName());
            if (!stmt.execute()) {
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        try {
            String deleteQuery = String.format("DELETE FROM %s WHERE id=?", itemsTable);
            PreparedStatement stmt = cn.prepareStatement(deleteQuery);
            stmt.setLong(1, Long.parseLong(id));
            return stmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Item> findAll() {
        return getItems(item -> true);
    }

    @Override
    public List<Item> findByName(String key) {
        return getItems(item -> item.getName().equals(key));
    }

    @Override
    public Item findById(String id) {
        return getItems(item -> item.getId().equals(id)).get(0);
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    private List<Item> getItems(Predicate<Item> condition) {
        List<Item> items = new ArrayList<>();

        try {
            String selectAll = String.format("SELECT * FROM %s", itemsTable);
            PreparedStatement stmt = cn.prepareStatement(selectAll);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                Item item = new Item(name);
                item.setId(String.valueOf(id));
                if (condition.test(item)) {
                    items.add(item);
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return items;
    }

}
