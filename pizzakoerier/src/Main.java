import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Rit rit = new Rit();
        rit.afstand();

    }
}

class Rit {

    public void afstand() {
        Scanner scanner = new Scanner(System.in);

        int aantalRitten = Integer.parseInt(scanner.nextLine());
        //System.out.println(aantalRitten);

        for(int i = 0; i < aantalRitten; i ++) {
            //System.out.println("rit: " + (i + 1));

            int vertices = scanner.nextInt(); // vertices
            int edges = scanner.nextInt(); // edges

            Graph graph = new Graph(vertices);

            for(int j = 0; j < edges; j++) {

                int startPunt = scanner.nextInt();  // src
                int eindPunt = scanner.nextInt();   // dis
                int gewicht = scanner.nextInt();    // weight / tijd dat wij hebben!

                graph.addEdge(startPunt, eindPunt, gewicht);
                graph.addEdge(eindPunt, startPunt, gewicht);

            }

            int aantalBestellingen = scanner.nextInt(); // edges

            int tijdstip;
            int winst;
            int thuis = 0;
            int[] sourceVertex = new int[3];
            int onderweg = 0;
            ArrayList<Integer> onder = new ArrayList<>();
            int verdiend = 0;
            ArrayList<Integer> ver = new ArrayList<>();

            for(int j = 0; j < aantalBestellingen; j++) {

                int pizzeria = scanner.nextInt();   // locatie a
                int leverplaats = scanner.nextInt();// locatie b
                tijdstip = scanner.nextInt();   // tijdstip
                winst = scanner.nextInt();      // winst

                if(j > 0) {
                    sourceVertex = graph.dijkstra_GetMinDistances(sourceVertex[1], pizzeria);
                    onderweg += sourceVertex[2];
                    sourceVertex = graph.dijkstra_GetMinDistances(sourceVertex[1], leverplaats);
                    onderweg += sourceVertex[2];
                } else {
                    sourceVertex = graph.dijkstra_GetMinDistances(thuis, pizzeria);
                    onderweg += sourceVertex[2];
                    sourceVertex = graph.dijkstra_GetMinDistances(sourceVertex[1], leverplaats);
                    onderweg += sourceVertex[2];
                }

                System.out.println(onderweg);

                if(onderweg < tijdstip) {
                    onderweg = tijdstip;
                    verdiend += winst;
                } else if (onderweg > tijdstip) {
                    onderweg = tijdstip;
                    //System.out.println("het is onmogelijk om op tijd alle bestellingen te kunnen doen.");
                    break;
                }

                //onder.add(tijdstip);
                //ver.add(winst);
            }

            // alle mogelijk onderwegs achterhalen??? en dan de beste kiezen
            //System.out.println(onderweg);
            //System.out.println(onder);
            //System.out.println(ver);

            ///
            /// ERROR MET 0 OP TWEEDE! KIJK HET EENS
            ///

            System.out.println((i + 1) + " " + verdiend);
            System.out.println("------");
        }
    }
}

class Graph {
    int vertices;
    int matrix[][];

    public Graph(int vertex) {
        this.vertices = vertex;
        matrix = new int[vertex][vertex];
    }

    public void addEdge(int source, int destination, int weight) {
        matrix[source][destination] = weight;

        matrix[destination][source] = weight;
    }

    int getMinimumVertex(boolean [] mst, int [] key){
        int minKey = Integer.MAX_VALUE;
        int vertex = -1;
        for (int i = 0; i <vertices ; i++) {
            if(mst[i] ==false && minKey>key[i]){
                minKey = key[i];
                vertex = i;
            }
        }
        return vertex;
    }

    public int[] dijkstra_GetMinDistances(int sourceVertex, int bestemmingVertex){
        boolean[] spt = new boolean[vertices];
        int [] distance = new int[vertices];
        int INFINITY = Integer.MAX_VALUE;

        int[] data = new int[3];

        for (int i = 0; i < vertices ; i++) {
            distance[i] = INFINITY;
        }

        distance[sourceVertex] = 0;

        for (int i = 0; i < vertices ; i++) {

            int vertex_U = getMinimumVertex(spt, distance);

            // geen verbindingen meer mogelijk
            if(vertex_U == -1) {
                break;
            }

            spt[vertex_U] = true;

            for (int vertex_V = 0; vertex_V <vertices ; vertex_V++) {
                if(matrix[vertex_U][vertex_V]>0){

                    if(spt[vertex_V]==false && matrix[vertex_U][vertex_V]!=INFINITY){

                        int newKey =  matrix[vertex_U][vertex_V] + distance[vertex_U];
                        if(newKey<distance[vertex_V])
                            distance[vertex_V] = newKey;
                    }
                }
            }
        }


        data[0] = sourceVertex;
        data[1] = bestemmingVertex;
        data[2] = distance[bestemmingVertex];

        System.out.println("Van vortex " + sourceVertex + " naar pizzeria " + bestemmingVertex + " kost: " + distance[bestemmingVertex]);


        return data;
    }
}