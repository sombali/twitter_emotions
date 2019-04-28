package main;

import java.util.ArrayList;
import java.util.List;

public class Input {
    private List<String> trainingSet = new ArrayList<String>();
    private List<Integer> trainingSetResults = new ArrayList<Integer>();
    private List<String> realDocuments = new ArrayList<String>();

    public List<String> getTrainingSet() {
        return trainingSet;
    }


    public List<Integer> getTrainingSetResults() {
        return trainingSetResults;
    }

    public List<String> getRealDocuments() {
        return realDocuments;
    }

    public Input() {
    }
}