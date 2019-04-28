package main;

import java.util.*;

public class Reader {
    private final Input input = new Input();
    private Scanner scanner;

    public Input getInput() {
        return input;
    }

    public Reader() {
        scanner = new Scanner(System.in);
    }

    public List<String> getTrainingSet() {
        return input.getTrainingSet();
    }

    public List<Integer> getTrainingSetResults() {
        return input.getTrainingSetResults();
    }

    public List<String> getRealDocuments() {
        return input.getRealDocuments();
    }

    public void read() {
        iterateOverTrainingSet();
        iterateOverTrainingSetResults();
        iterateOverRealDocuments();

        scanner.close();
    }

    private void iterateOverTrainingSet() {
        String nextLine;
        for(int i = 0; i < 5; i++) {
            nextLine = scanner.nextLine();
            getTrainingSet().add(nextLine);
        }
    }

    private void iterateOverTrainingSetResults() {
        String nextLine;
        for (int i = 0; i < 5; i++) {
            nextLine = scanner.nextLine();
            if(!getTrainingSet().get(i).isEmpty()) {
                int value = Integer.parseInt(nextLine);
                getTrainingSetResults().add(value);
            } else {
                getTrainingSetResults().add(-1);
            }
        }
    }

    private void iterateOverRealDocuments() {
        String nextLine;
        for(int i = 0; i < 1; i++) {
            nextLine = scanner.nextLine();
            getRealDocuments().add(nextLine);
        }
    }

}
