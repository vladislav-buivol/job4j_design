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


    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("chapter_001/data/consoleChatBot/log.txt", "chapter_001/data/consoleChatBot/answers.txt");
        cc.run();
    }

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            String userInput;
            Bot bot = new Bot(CONTINUE, bf);
            User user = new User(new Scanner(System.in), bf);
            while (!(userInput = user.say()).equals(OUT)) {
                bot.updateStatus(userInput);
                bot.say();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void record(BufferedWriter bf, String userInput, String s) {
        try {
            bf.write(String.format(s, userInput, System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class User {
        private final BufferedWriter bf;
        private final Scanner scanner;

        private User(Scanner scanner, BufferedWriter bf) {
            this.bf = bf;
            this.scanner = scanner;
        }

        private String say() {
            String input = this.scanner.nextLine();
            record(this.bf, input, "User: %s %s");
            return input;
        }

    }

    private class Bot {
        private String status;
        private final List<String> answers;
        private final BufferedWriter bf;

        public Bot(String status, BufferedWriter bf) {
            this.status = status;
            this.answers = listOfAnswers(botAnswers);
            this.bf = bf;
        }

        public void say() {
            String botInput;
            botInput = "";
            if (!status.equals(STOP)) {
                botInput = botAnswer(this.answers);
                record(bf, botInput, "Bot: %s %s");
            }
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