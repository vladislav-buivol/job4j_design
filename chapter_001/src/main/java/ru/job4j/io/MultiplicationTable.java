package ru.job4j.io;

public class MultiplicationTable {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < 11; i++){
            for(int j = 0; j < 11; j++){
                sb.append(String.format("%s %s %s = %s\n",i, "*", j, i*j));
            }
            sb.append("\n");
        }
        ResultFile.write("result.txt",sb.toString());
    }
}
