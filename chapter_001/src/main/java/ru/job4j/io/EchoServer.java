package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;

public class EchoServer {
    private static HashMap<String, String> values;

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (true) {
                values = new HashMap<>();
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        if (str.startsWith("GET")) {
                            parseParameters(str.split(" ")[1].substring(2));
                        }
                        System.out.println(str);
                    }
                    out.write("HTTP/1.1 200 OK\r\n\\".getBytes());
                    if (exit()) {
                        System.out.println("Finishing work..");
                        break;
                    }
                }
            }
        }
    }

    private static boolean exit() {
        return values.get("msg").equals("Bye");
    }

    private static void parseParameters(String substring) {
        Arrays.stream(substring.split("&"))
                .forEach(EchoServer::addValue);
    }

    private static void addValue(String el) {
        String[] parts = el.split("=");
        values.put(parts[0], parts[1]);
    }
}