import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Dictee dictee = new Dictee();
        dictee.verbeteren();

    }
}

class Dictee {
    public void verbeteren() {

        Scanner scanner = new Scanner(System.in);

        int aantalZinnen = Integer.parseInt(scanner.nextLine());

        for(int x = 0; x < aantalZinnen; x++) {

            int substitutie, verwijdering, toevoeging, transpositie, punten;

            String ingediendeZin = scanner.nextLine();
            String correcteZin = scanner.nextLine();

            int lengteIngediendeZin = ingediendeZin.length();
            int lengteCorrecteZin = correcteZin.length();

            final int maxDistance = lengteIngediendeZin + lengteCorrecteZin;

            // fouten/punten in deze zin
            int[][] distance = new int[lengteIngediendeZin + 2][lengteCorrecteZin + 2];

            distance[0][0] = maxDistance;

            for(int i = 0; i <= lengteIngediendeZin; i++) {
                distance[i+1][0] = maxDistance;
                distance[i+1][1] = i;
            }

            for(int j = 0; j <= lengteCorrecteZin; j++) {
                distance[0][j+1] = maxDistance;
                distance[1][j+1] = j;
            }

            int[] da = new int[9999];

            for (int k = 0; k <= maxDistance; k++) {
                da[k] = 0;
            }

            for (int i = 1; i <= lengteIngediendeZin; i++) {
                //int db = 0;
                for (int j = 1; j <= lengteCorrecteZin; j++) {

                    //int k = da[correcteZin.charAt(j-1)];
                    //int l = db;

                    if(ingediendeZin.charAt(i-1) == correcteZin.charAt(j-1)) {
                       punten = 0;
                    } else if ((((int)ingediendeZin.charAt(i-1)) + 32) == ((int)correcteZin.charAt(j-1)) || ((int)ingediendeZin.charAt(i-1)) == ((int)correcteZin.charAt(j-1) + 32)) {
                        punten = 1;
                    } else {
                       punten = 2;
                    }

                    /*if(punten == 0) {
                        db = j;
                    }*/

                    substitutie = distance[i][j] + punten;
                    verwijdering = distance[i+1][j] + 2;
                    toevoeging = distance[i][j+1] + 2;
                    //transpositie = distance[k][l] + (i-k-1) + 2 + (j-l-1);

                    distance[i+1][j+1] = Math.min( Math.min(substitutie, verwijdering), toevoeging /*Math.min(toevoeging, transpositie)*/);

                }
                da[ingediendeZin.charAt(i-1)] = i;
            }
            System.out.println((x + 1) +  " " + distance[lengteIngediendeZin+1][lengteCorrecteZin+1]);
        }
    }
}
