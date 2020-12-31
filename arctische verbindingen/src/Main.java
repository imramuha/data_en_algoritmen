import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

        for(int x = 0; x < aantalTestCases; x++) {

            // 1-100
            int aantalSatellietverbindingen = scanner.nextInt();
            // > aantal satellietverbindingen <= 500
            int aantalOnderzoekcentra = scanner.nextInt();

            int nodes[][] = new int[aantalOnderzoekcentra][2];

            for(int y = 0; y < nodes.length; y++) {

                // 0-10000
                int xcoordinaat = scanner.nextInt();
                int ycoordinaat = scanner.nextInt();

                nodes[y][0] = xcoordinaat;
                nodes[y][1] = ycoordinaat;

            }

            List<Integer> gewichten = new ArrayList<>();

            // all my edges & gewicht;
            // elke node met andere node vergelijken en de gewichten opnemen;;
            for(int y = 0; y < nodes.length; y++) {

                int[] startPunt = nodes[y];

                for(int z = y + 1; z < nodes.length; z++) {

                    int[] eindPunt = nodes[z];

                    gewichten.add(berekentAfstand(startPunt, eindPunt));
                    // deze afstanden ergens bewaren;

                }
            }

            Collections.sort(gewichten);
            System.out.println(gewichten.get(aantalSatellietverbindingen-1));
        }

    }

   public int berekentAfstand(int[] startPunt, int[] eindPunt) {

        int totalX = startPunt[0] - eindPunt[0];
        int totalY = startPunt[1]- eindPunt[1];

        return (int)Math.ceil(Math.sqrt(Math.pow(totalX,2) + Math.pow(totalY,2)));
        
    }
}