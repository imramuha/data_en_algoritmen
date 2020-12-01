import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Voer de input voor output in: ");

        int aantalTestGevallen = Integer.parseInt(reader.nextLine());
        int aantalRegelsPerTestGeval = 5;

        Controleer controleer = new Controleer();

        // string array met onze data :) die wij op het einde zullen tonen;
        System.out.println(aantalTestGevallen);

        for(int i = 0; i < aantalTestGevallen; i++) {
            System.out.println(i);
            for (int j = 0; j <= aantalRegelsPerTestGeval; j++) {
                System.out.println(j);

                String testGeval = reader.nextLine();
                System.out.println(testGeval);

                // hier gaan wij een functie uitvoeren die weergeeft of het true of onzin is, en die gaan wij dan displayed;
                controleer.testGeval(testGeval);
            }
        }
    }
}

class Controleer {
    public void testGeval(String testGeval) {

        int aantalKoppelsPerTestGeval = testGeval.length() / 2;

        if(aantalKoppelsPerTestGeval % 2 == 0) {
            int aantalMogelijkeControles = aantalKoppelsPerTestGeval / 2;
            int g = 0;
            int h = testGeval.length() - 2;


            for(int x = aantalMogelijkeControles; x > 0; x--) {
                System.out.println("[" + g + "." + (g + 2) + "]");
                System.out.println(g);

                String frontString = testGeval.substring(g, (g + 2));
                String endString = testGeval.substring((h - 2), h);

                System.out.println(frontString);
                System.out.println(endString);

                h = h - 2;
                g = g + 2;
            }

            System.out.println(aantalMogelijkeControles);
        } else {
            int aantalMogelijkeControles = (aantalKoppelsPerTestGeval - 1) / 2;
            System.out.println(aantalMogelijkeControles);
        }
    }
}
