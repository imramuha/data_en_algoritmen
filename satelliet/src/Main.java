import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Satelliet satelliet = new Satelliet();
        satelliet.scan();

    }
}

class Satelliet {
    public void scan() {

        Scanner scanner = new Scanner(System.in);

        int aantalTestGevallen = Integer.parseInt(scanner.nextLine());
        System.out.println(aantalTestGevallen);

        for(int x = 0; x < aantalTestGevallen; x++) {

            // max 10000 x 1 of 0
            String scanSignaal = scanner.nextLine();

            // tussen 1 en 20
            int aantalLettercodes = Integer.parseInt(scanner.nextLine());

            for (int y = 0; y < aantalLettercodes; y++) {

                // hoofdletter
                String letter = scanner.nextLine();

                // 20 * 0 of 1
                String signaal = scanner.nextLine();

                System.out.println(letter);
                System.out.println(signaal);
            }
        }

    }
}
