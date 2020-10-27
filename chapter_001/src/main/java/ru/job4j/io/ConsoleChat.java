package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final ArrayList<String> log = new ArrayList<>();


    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("chapter_001/data/consoleChatBot/log.txt", "chapter_001/data/consoleChatBot/answers.txt");
        cc.run();
    }

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        startChat();
        saveChat();
    }

    private void startChat() {
        String userInput;
        Bot bot = new Bot(CONTINUE, this.log);
        User user = new User(new Scanner(System.in), this.log);
        while (!(userInput = user.say()).equals(OUT)) {
            bot.updateStatus(userInput);
            bot.say();
        }
    }

    private void saveChat() {
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            this.log.forEach(el -> record(bf, el));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void record(BufferedWriter bf, String userInput) {
        try {
            bf.write(userInput + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class User {
        private final Scanner scanner;
        private ArrayList<String> log;

        private User(Scanner scanner, ArrayList<String> log) {
            this.scanner = scanner;
            this.log = log;
        }

        private String say() {
            String input = this.scanner.nextLine();
            log.add("User: " + input);
            return input;
        }

    }

    private class Bot {
        private String status;
        private final List<String> answers;
        private ArrayList<String> log;

        public Bot(String status, ArrayList<String> log) {
            this.status = status;
            this.answers = listOfAnswers(botAnswers);
            this.log = log;
        }

        public void say() {
            String botInput;
            botInput = "";
            if (!status.equals(STOP)) {
                botInput = botAnswer(this.answers);
            }
            log.add("Bot: " + botInput);
            System.out.println(botInput);
        }

        private void updateStatus(String userInput) {
            if (userInput.equals(CONTINUE) || userInput.equals(STOP) || userInput.equals(OUT)) {
                this.status = userInput;
            }
        }

        private String botAnswer(List<String> answers) {
            Random random = new Random();
            return answers.get(random.nextInt(answers.size()));
        }

        private List<String> listOfAnswers(String botAnswers) {
            List<String> answers = new ArrayList<>();
            try (BufferedReader in = new BufferedReader(new FileReader(botAnswers))) {
                in.lines()
                        .forEach(answers::add);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return answers;
        }
    }
}