package tracker;

public class StubInput implements Input {
    private String[] answer;
    private int position = 0;

    public StubInput(String[] answer) {
        this.answer = answer;
    }
    @Override
    public String askStr(String question) {
        return this.answer[position++];
    }
    @Override
    public int askInt(String question) {
        return Integer.parseInt(this.askStr(question));
    }
    @Override
    public int askInt(String question, int max) {
        return askInt(question);
    }
}
