package tracker;

import propertyreader.DatabaseProperties;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


public class SqlTracker implements Store {
    private Connection cn;
    private String itemsTable = "items";
    private final Connection connection;

    public static void main(String[] args) {
        Input validate = new ValidateInput(
                new ConsoleInput()
        );
        /*try (Store tracker = new SqlTracker()) {
            tracker.init();
            List<UserAction> actions = new ArrayList<>();
            actions.add(new CreateAction());
            new StartUI().init(validate, tracker, actions);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }


    public SqlTracker(Connection connection) {
        this.connection = connection;
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
        try (final PreparedStatement statement = this.connection
                .prepareStatement("insert into items (name) values (?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getString(1));
                    return item;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("Could not create new user");
    }

    @Override
    public boolean replace(String id, Item item) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        String deleteQuery = String.format("DELETE FROM %s WHERE id=?", itemsTable);
        try (PreparedStatement stmt = connection.prepareStatement(deleteQuery)) {
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
        return getItem(item -> item.getId().equals(id));
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    private List<Item> getItems(Predicate<Item> condition) {
        List<Item> items = new ArrayList<>();
        String selectAll = String.format("SELECT * FROM %s", itemsTable);
        try (PreparedStatement stmt = connection.prepareStatement(selectAll);
             ResultSet result = stmt.executeQuery()) {
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

    private Item getItem(Predicate<Item> condition) {
        String selectAll = String.format("SELECT * FROM %s", itemsTable);
        try (PreparedStatement stmt = connection.prepareStatement(selectAll);
             ResultSet result = stmt.executeQuery()) {
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                Item item = new Item(name);
                item.setId(String.valueOf(id));
                if (condition.test(item)) {
                    return item;
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

}
