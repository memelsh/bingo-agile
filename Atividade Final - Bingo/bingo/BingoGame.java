package bingo;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BingoGame implements BingoObserver {
    private Set<Integer> drawnNumbers = new HashSet<>();
    private BingoCard playerCard;
    private boolean running = true;

    public BingoGame() {
        playerCard = new BingoCard();
        playerCard.addObserver(this);
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- BINGO AGILE INICIADO ---");
        playerCard.printCard();

        while (running) {
            System.out.println("Pressione ENTER para sortear...");
            scanner.nextLine();

            int num;
            do { num = (int) (Math.random() * 75) + 1; } while (drawnNumbers.contains(num));
            drawnNumbers.add(num);

            System.out.println("Sorteado: " + num);
            if (playerCard.markNumber(num)) {
                System.out.println("!!! MARCOU O " + num + " !!!");
                playerCard.printCard();
            } else {
                System.out.println("Nao tem na cartela.");
            }
        }
        scanner.close();
    }

    public void onBingo() {
        System.out.println("\n*** BINGO! VOCE GANHOU! ***");
        running = false;
    }

    public static void main(String[] args) {
        new BingoGame().play();
    }
}
