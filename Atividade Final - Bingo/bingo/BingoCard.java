package bingo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BingoCard {
    private static final int CARD_SIZE = 24; // Exigência atendida
    private Set<Integer> numbers;
    private List<BingoObserver> observers;

    public BingoCard() {
        this.numbers = new HashSet<>();
        this.observers = new ArrayList<>();
        generateCard();
    }

    private void generateCard() {
        while (numbers.size() < CARD_SIZE) {
            numbers.add((int) (Math.random() * 75) + 1);
        }
    }

    public void addObserver(BingoObserver observer) {
        observers.add(observer);
    }

    public boolean markNumber(int number) {
        boolean marked = numbers.remove(number);
        if (marked && isWinner()) notifyObservers();
        return marked;
    }

    public boolean isWinner() { return numbers.isEmpty(); }

    private void notifyObservers() {
        for (BingoObserver obs : observers) obs.onBingo();
    }

    public void printCard() { System.out.println("Cartela (Faltam marcar): " + numbers); }
}
