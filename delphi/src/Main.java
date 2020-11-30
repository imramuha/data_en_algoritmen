
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Orakel abrakadabra = new Orakel();
        abrakadabra.vraag(scanner);
    }
}

class Orakel {

    public void vraag(Scanner scanner) {

        int aantalTestGevallen = Integer.parseInt(scanner.next());

        for (int xx = 0; xx < aantalTestGevallen; xx++) {


            int visieTotJaar = Integer.parseInt(scanner.next());
            int aantalNeeVragen = Integer.parseInt(scanner.next());
            int aantalJaVragen = 0;
            int tmp;

            int[][] rooster = new int[aantalNeeVragen + 1][visieTotJaar + 1];

            for (int i = 1; i <= aantalNeeVragen; i++){

                rooster[i][1] = 1;
                rooster[i][0] = 0;

            }

            for (int j = 1; j <= visieTotJaar ; j++) rooster[1][j] = j;

            for (int i = 2; i <= aantalNeeVragen; i++){
                for (int j = 2; j <= visieTotJaar ; j++){

                    rooster[i][j] = Integer.MAX_VALUE;

                    for (int x = 1; x <= j; x++){

                        tmp = Math.max(rooster[i-1][x-1], 1+rooster[i][j-x]);
                        if (tmp < rooster[i][j]) rooster[i][j] = tmp;

                    }
                }

            }

            // fout bij alles te kunnen tonen; momenteel de laatste fout niet gezien;
            for (int rij = 0; rij <= visieTotJaar; rij++)
            {
                if (rooster[aantalNeeVragen][rij] > aantalJaVragen)
                {
                    aantalJaVragen = rooster[aantalNeeVragen][rij];
                }
            }
            System.out.println((xx + 1) + " " + aantalJaVragen);
        }
    }
}
