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
    private static HashMap<String, Parameter> values;
    private static final String HELLO = "Hello, dear friend.";
    private static final String OK_STATUS = "HTTP/1.1 200 OK\r\n\\";
    private static final String MSG = "msg";

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
                    sendResponse(out);
                    if (exit()) {
                        System.out.println("Finishing work..");
                        break;
                    }
                }
            }
        }
    }

    private static void sendPositiveResponse(OutputStream out) throws IOException {
        out.write(OK_STATUS.getBytes());
    }

    private static void sendResponse(OutputStream out) throws IOException {
        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
        Parameter parameter = values.get(MSG);
        if (parameter.type == ParameterType.Hello) {
            out.write(HELLO.getBytes());
        } else {
            out.write(parameter.value.getBytes());
        }
    }

    private static boolean exit() {
        return values.get(MSG).type == ParameterType.Exit;
    }

    private static void parseParameters(String substring) {
        Arrays.stream(substring.split("&"))
                .forEach(EchoServer::addValue);
    }

    private static void addValue(String el) {
        String[] parts = el.split("=");
        Parameter param = new Parameter(parts[0], parts[1], Parameter.valueOf(parts[1]));
        values.put(parts[0], param);
    }

    private enum ParameterType {
        Exit,
        Hello,
        Any
    }

    private static class Parameter {
        private String key;
        private String value;
        private ParameterType type;

        public Parameter(String key, String value, ParameterType parameter) {
            this.key = key;
            this.value = value;
            this.type = parameter;
        }

        public static ParameterType valueOf(String value) {
            value = value.toLowerCase();
            if (ParameterType.Hello.toString().toLowerCase().equals(value)) {
                return ParameterType.Hello;
            } else if (ParameterType.Exit.toString().toLowerCase().equals(value)) {
                return ParameterType.Exit;
            }
            return ParameterType.Any;
        }
    }
}