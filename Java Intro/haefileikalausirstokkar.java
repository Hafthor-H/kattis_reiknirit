import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class haefileikalausirstokkar {
    public static void main(String[] args) {
        // List<String> no_skill = new ArrayList<>();
        // List<String> deck = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        scanner.nextLine();
        List<String> no_skill = getLines(scanner, n);

        int k = scanner.nextInt();
        scanner.nextLine();

        for (int j = 0; j < k; j++) {
            boolean skilled = true;
            List<String> deck = getLines(scanner, 6);
            for (int l = 0; l < no_skill.size(); l++) {
                if (deck.contains(no_skill.get(l))) {
                    System.out.println("Hæfileikalaust Drasl");
                    skilled = false;
                    break;
                }
            }
            if (skilled) {
                System.out.println("Fínn Stokkur");
            }

        }
    }

    public static List<String> getLines(Scanner scanner, int n) {
        List<String> items = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            items.add(line);
        }
        return items;
    }
}
