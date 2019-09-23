package stu.napls.ciprogsolu.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileToolbox {
    public static String readFileAsString(String path) throws IOException {
        StringBuilder content = new StringBuilder();
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            content.append(line);
        }
        bufferedReader.close();
        fileReader.close();
        return content.toString();
    }

    public static ArrayList<String> readFileAsList(String path) throws IOException {
        ArrayList<String> content = new ArrayList<>();
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            content.add(line);
        }
        bufferedReader.close();
        fileReader.close();
        return content;
    }

    public static void writeFileFromString(String path, String content) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write(content);
        fileWriter.close();
    }

}
