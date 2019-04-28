package main;

import java.util.Objects;

public class Word {
    private int word;

    private double probabilityOfPositive;
    private double probabilityOfNegative;

    private int numberInPositiveCase;
    private int numberInNegativeCase;

    public Word(int word) {
        this.word = word;
    }

    public int getWord() {
        return word;
    }

    public double getProbabilityOfPositive() {
        return probabilityOfPositive;
    }

    public double getProbabilityOfNegative() {
        return probabilityOfNegative;
    }

    public int getNumberInPositiveCase() {
        return numberInPositiveCase;
    }

    public int getNumberInNegativeCase() {
        return numberInNegativeCase;
    }

    public void increaseNumberInPositiveCase() {
        this.numberInPositiveCase += 1;
    }

    public void increaseNumberInNegativeCase() {
        this.numberInNegativeCase += 1;
    }

    public void setProbabilityOfPositive(double probabilityOfPositive) {
        this.probabilityOfPositive = probabilityOfPositive;
    }

    public void setProbabilityOfNegative(double probabilityOfNegative) {
        this.probabilityOfNegative = probabilityOfNegative;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Word)) return false;
        Word word1 = (Word) o;
        return getWord() == word1.getWord();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWord(), getProbabilityOfPositive(), getProbabilityOfNegative(), getNumberInPositiveCase(), getNumberInNegativeCase());
    }
}
