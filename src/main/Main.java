package main;

public class Main {

    public static void main(String[] args) {
        Reader reader = new Reader();
        reader.read();

        Bayes bayes = new Bayes();
        bayes.start(reader.getInput());
    }
}
