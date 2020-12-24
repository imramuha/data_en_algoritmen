import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Parcour parcour = new Parcour();
        parcour.bereken();

    }
}

class Parcour {

    public void bereken() {

        Scanner scanner = new Scanner(System.in);

        int aantalParcours = Integer.parseInt(scanner.nextLine());
        // System.out.println("parc: " + aantalParcours);

        for(int x = 0; x < aantalParcours; x++) {

            int aantalKnooppunten = scanner.nextInt();          // vertices

            int aantalVerbindingen = scanner.nextInt();         // edges

            int[][] graph = new int[aantalVerbindingen][];

            for(int i = 0; i < aantalVerbindingen; i++) {
                graph[i] = new int[aantalVerbindingen];
            }

            for(int i = 0; i < aantalVerbindingen; i++ ) {
                int beginknooppuntnummer = scanner.nextInt();   // start
                int eindknooppuntnummer = scanner.nextInt();    // eind
                int strafpunten = scanner.nextInt();            // gewicht

                graph[i][0] = beginknooppuntnummer;
                graph[i][1] = eindknooppuntnummer;
                graph[i][2] = strafpunten;
            }

            BellmanFord(x, graph, aantalKnooppunten, aantalVerbindingen, 0);
        }
    }

    static void BellmanFord(int aantalParcours, int graph[][], int aantalKnooppunten, int aantalVerbindingen, int src) {


        int resultaat;
        // dist van alles knooppunten naar inf
        int dis[] = new int[aantalKnooppunten];

        for (int i = 0; i < aantalKnooppunten; i++) {
            dis[i] = Integer.MAX_VALUE;
        }

        // source als 0
        dis[src] = 0;

        // kortste pad
        for (int i = 0; i < aantalKnooppunten; i++) {
           for (int j = 0; j < aantalVerbindingen; j++) {

               // System.out.println(graph[j][0]); // startpunt
               // System.out.println(graph[j][1]); // eindpunt
               // System.out.println(graph[j][2]); // gewicht

                if (dis[graph[j][0]-1] != Integer.MAX_VALUE && dis[graph[j][0]-1] + graph[j][2] < dis[graph[j][1]-1]) {
                    //System.out.println(j);
                    dis[graph[j][1]-1] = dis[graph[j][0]-1] + graph[j][2];
                }

            }
        }

        resultaat = dis[dis.length-1];

        // check voor negatieve cycluses
        for (int i = 0; i < aantalVerbindingen; i++) {

            if (dis[graph[i][0]-1] != Integer.MAX_VALUE && dis[graph[i][0]-1] + graph[i][2] < dis[graph[i][1]-1]) {
                resultaat = Integer.MIN_VALUE;
            }

        }

        if(resultaat == Integer.MIN_VALUE) {
            System.out.println(aantalParcours + " " + "min oneindig");
        } else if (resultaat == Integer.MAX_VALUE) {
            System.out.println(aantalParcours + " " + "plus oneindig");
        } else {
            System.out.println(aantalParcours + " " + dis[dis.length-1]);
        }

    }

}
