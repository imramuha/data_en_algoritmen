import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Orakel abrakadabra = new Orakel();
        abrakadabra.vraag();

    }
}

class Orakel {
    public void vraag() {

        Scanner scanner = new Scanner(System.in);
        int aantalTestGevallen = Integer.parseInt(scanner.nextLine());

        for (int x = 0; x < aantalTestGevallen; x++) {

            int aantalNeeVragen = scanner.nextInt();
            int visieTotJaar = scanner.nextInt();
            int aantalJaVragen = 0;
            int temp;

            //System.out.println(aantalNeeVragen);
            //System.out.println(visieTotJaar);

            int[][] rooster = new int[aantalNeeVragen+1][visieTotJaar+1];

            for (int i = 1; i <= aantalNeeVragen; i++){
                rooster[i][1] = 1;
                rooster[i][0] = 0;
            }

            for (int j = 1; j <= visieTotJaar; j++) rooster[1][j] = j;

            for (int i = 2; i <= aantalNeeVragen; i++){
                for (int j = 2; j <= visieTotJaar; j++){

                    rooster[i][j] = Integer.MAX_VALUE;

                    for (int k = 1; k <= j; k++){

                        temp = Math.max(rooster[i-1][k-1], 1+rooster[i][j-k]);

                        if (temp < rooster[i][j]) {

                            rooster[i][j] = temp;

                        }
                    }
                }

            }

            for (int z = 0; z <= visieTotJaar; z++) {
                // System.out.println(rooster[aantalNeeVragen][z]);
                if (rooster[aantalNeeVragen][z] > aantalJaVragen) {

                    aantalJaVragen = rooster[aantalNeeVragen][z];

                }
            }

            System.out.println((x+1) + " " + aantalJaVragen);
        }
    }
}
