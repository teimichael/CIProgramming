package stu.napls.ciprogsolu.core;

import stu.napls.ciprogsolu.util.FileToolbox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class P2015S {

    private List<String> contentList;

    public P2015S() throws IOException {
        // Read file as an ArrayList
        this.contentList = FileToolbox.readFileAsList(this.getClass().getClassLoader().getResource("p2015s/file/program.txt").getPath());
        for (int i = 0; i < this.contentList.size(); i++) {
            System.out.println(contentList.get(i));
        }
    }

    public void doQ1() {
        // Read file as an ArrayList
        int counter = 0;
        for (int i = 0; i < this.contentList.size(); i++) {
            if (this.contentList.get(i).contains(";")) {
                counter++;
            }
        }
        System.out.println("The number of semi-colons is: " + counter);
    }

    // All the lines containing in the *main* function.
    public void doQ2() {
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < this.contentList.size(); i++) {
            if (this.contentList.get(i).contains("main")) {

            }
        }
    }

    public void doQ3() {

    }

    public void doQ4() {

    }

    public void doQ5() {

    }

    public void doQ6() {

    }

    public void doQ7() {

    }

}
