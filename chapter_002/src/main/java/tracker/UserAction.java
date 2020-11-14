package tracker;

public interface UserAction {
    String name();
    boolean execute(Input input, Store tracker);
}
