import java.util.ArrayList;
import java.util.List;
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

        for(int x = 0; x < aantalTestGevallen; x++) {

            String output = new String();

            // max 10000 x 1 of 0
            String scan = scanner.nextLine();
            int maxMogelijkheden = scan.length();

            List<List<String>> relatieLettersSignalen = new ArrayList<List<String>>();

            List<String> letters = new ArrayList<>();
            List<String> signalen = new ArrayList<>();
            List<String> resultaten = new ArrayList<>();

            relatieLettersSignalen.add(letters);
            relatieLettersSignalen.add(signalen);
            relatieLettersSignalen.add(resultaten);

            // tussen 1 en 20
            int aantalLettercodes = Integer.parseInt(scanner.nextLine());

            for (int y = 0; y < aantalLettercodes; y++) {

                // letter: hoofdletter;
                // signaal: 20 * 0 of 1;
                String letter = scanner.nextLine();
                String signaal = scanner.nextLine();

                relatieLettersSignalen.get(0).add(letter);
                relatieLettersSignalen.get(1).add(signaal);
            }

            for(int y = 0; y < maxMogelijkheden; y++) {

                //print elke letter
                for(int z = maxMogelijkheden; z > y; z--) {

                    // i -> zijn de signalen
                    for(int i = relatieLettersSignalen.get(1).size() - 1; i >= 0; i--) {
                        if (i >= 0 && scan.substring(y, z).equals(relatieLettersSignalen.get(1).get(i))) {

                            // als match, voegen wij letter toe aan resultaten;
                            relatieLettersSignalen.get(2).add(relatieLettersSignalen.get(0).get(i));
                            y = z - 1;
                            z = y;
                            break;

                        } else if (i == 0 && y == (z - 1)) {
                            output = "ONMOGELIJK";
                            break;
                        }
                    }
                }
            }

            if(output.equals("ONMOGELIJK")) {
                System.out.println((x + 1) + " " + output);
            } else {
                for(int y = 0; y < relatieLettersSignalen.get(2).size(); y++) {
                    output += relatieLettersSignalen.get(2).get(y);
                }
                System.out.println((x + 1) + " " + output);
            }
        }

    }
}
