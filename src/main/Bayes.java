package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bayes {
    private Map<Integer, Word> vocabulary = new HashMap<>();

    private List<String> positiveCases = new ArrayList<>();
    private List<String> negativeCases = new ArrayList<>();

    private double positiveSentenceProbability;
    private double negativeSentenceProbability;



    public void start(Input input) {
        setVocabulary(input);
        setCases(input);
        getWords();
        calculateProbability();

        runOnRealDocuments(input);
    }

    private void setVocabulary(Input input) {
        for(String s : input.getTrainingSet()) {
            String[] splittedString = s.split("\t");
            for(String split : splittedString) {
                if(!split.isEmpty()) {
                    int value = Integer.parseInt(split);
                    if(!vocabulary.containsKey(value)) {
                        Word word = new Word(value);
                        vocabulary.put(value, word);
                    }
                }
            }
        }
    }

    private void setCases(Input input) {
        for(int i = 0; i < input.getTrainingSet().size(); i++) {
            if(input.getTrainingSetResults().get(i) == 1) {
                positiveCases.add(input.getTrainingSet().get(i));
            } else {
                negativeCases.add(input.getTrainingSet().get(i));
            }
        }
    }

    private void getWords() {
        positiveCase();
        negativeCase();
    }

    private void positiveCase() {
        for (String s : positiveCases) {
            String[] splittedString = s.split("\t");
            for(String split : splittedString) {
                if(!split.isEmpty()) {
                    int value = Integer.parseInt(split);

                    if(vocabulary.containsKey(value)) {
                        Word containedWord = vocabulary.get(value);
                        containedWord.increaseNumberInPositiveCase();
                    } else {
                        Word word = new Word(value);
                        word.increaseNumberInPositiveCase();
                        vocabulary.put(value, word);
                    }
                }
            }
        }
    }

    private void negativeCase() {
        for(String s : negativeCases) {
            String[] splittedString = s.split("\t");
            for(String split : splittedString) {
                if(!split.isEmpty()) {
                    int value = Integer.parseInt(split);
                    if(vocabulary.containsKey(value)) {
                        Word containedWord = vocabulary.get(value);
                        containedWord.increaseNumberInNegativeCase();
                    } else {
                        Word word = new Word(value);
                        word.increaseNumberInNegativeCase();
                        vocabulary.put(value, word);
                    }
                }
            }
        }
    }

    private void calculateProbability() {
        sentenceProbability();
        wordProbability();

    }

    private void sentenceProbability() {
        double allCases = positiveCases.size() + negativeCases.size();
        if(allCases != 0) {
            positiveSentenceProbability = (double)positiveCases.size()/allCases;
            negativeSentenceProbability = (double)negativeCases.size()/allCases;
        }
    }

    private void wordProbability() {
        int positiveWords = 0;
        int negativeWords = 0;

        for(Map.Entry<Integer, Word> entry : vocabulary.entrySet()) {
            Word w = entry.getValue();

            positiveWords += w.getNumberInPositiveCase();
            negativeWords += w.getNumberInNegativeCase();
        }

        for(Map.Entry<Integer, Word> entry : vocabulary.entrySet()) {
            Word w = entry.getValue();
            w.setProbabilityOfPositive((double)(w.getNumberInPositiveCase() + 1)/(positiveWords + vocabulary.size()));
            w.setProbabilityOfNegative((double)(w.getNumberInNegativeCase() + 1)/(negativeWords + vocabulary.size()));
        }
    }

    private void runOnRealDocuments(Input input) {
        double probabilityPositive = positiveSentenceProbability;
        double probabilityNegative = negativeSentenceProbability;

        double sentenceLogProbability = Math.log(probabilityPositive/probabilityNegative);

        double positiveProbabilityOfWord = 0;
        double negativeProbabilityOfWord = 0;
        List<String> realDocuments = input.getRealDocuments();
        for(String s : realDocuments) {
            String[] splittedString = s.split("\t");
            double sumLN = 0;

            for(String split : splittedString) {
                if(!split.isEmpty()) {
                    int value = Integer.parseInt(split);
                    if(vocabulary.containsKey(value)) {
                        positiveProbabilityOfWord = vocabulary.get(value).getProbabilityOfPositive();
                        negativeProbabilityOfWord = vocabulary.get(value).getProbabilityOfNegative();
                        double divided = positiveProbabilityOfWord / negativeProbabilityOfWord;

                        sumLN += Math.log(divided);

                    } else {
                        positiveProbabilityOfWord = 0.00000000000001;
                        negativeProbabilityOfWord = 0.00000000000001;
                        double divided = positiveProbabilityOfWord / negativeProbabilityOfWord;

                        sumLN += Math.log(divided);
                    }
                }
            }

            double probability = Math.log(sentenceLogProbability + sumLN);

            if(probability > 0) {
                System.out.println("1");
            } else {
                System.out.println("0");
            }
        }

    }
}
