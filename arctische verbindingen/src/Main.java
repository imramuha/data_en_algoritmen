import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Connectie connectie = new Connectie();
        connectie.afstand();

    }
}

class Connectie {

    public void afstand() {
        Scanner scanner = new Scanner(System.in);

        int aantalTestCases = Integer.parseInt(scanner.nextLine());
        System.out.println(aantalTestCases);

        for(int x = 0; x < aantalTestCases; x++) {

            // 1-100
            int aantalSatellietverbindingen = scanner.nextInt();

            // > aantal satellietverbindingen <= 500
            int aantalOnderzoekcentra = scanner.nextInt();

            System.out.println(aantalSatellietverbindingen + " " + aantalOnderzoekcentra);

            for(int y = 0; y < aantalOnderzoekcentra; y++) {

                // 0-10000
                int xcoordinaat = scanner.nextInt();
                int ycoordinaat = scanner.nextInt();

                System.out.println(xcoordinaat + " " + ycoordinaat);
            }
        }

    }

    public int CalcDistance() {
        int totalX = 0 - 150;
        int totalY = 600 - 750;

        System.out.println((int)Math.ceil(Math.sqrt(Math.pow(totalX,2) + Math.pow(totalY,2))));
        return (int)Math.ceil(Math.sqrt(Math.pow(totalX,2) + Math.pow(totalY,2)));
    }
}