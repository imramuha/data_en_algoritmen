import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Bar bar = new Bar();
        bar.serveer();

    }
}

class Bar {
    public void serveer() {

        Scanner scanner = new Scanner(System.in);

        // 1-100
        int aantalOpgaven = Integer.parseInt(scanner.nextLine());


        for(int x = 0; x < aantalOpgaven; x++) {

            // 1-100
            int aantalBarmannen = Integer.parseInt(scanner.nextLine());
            List<List<Integer>> aantalBarmannenPerRecept = new ArrayList<>();

            for (int y = 0; y < aantalBarmannen; y++) {

                List<Integer> recepten = new ArrayList<>();
                aantalBarmannenPerRecept.add(recepten);

                // A-Z alfabetisch, hoofdletters en max 26, zonder spaties
                String receptenBarman = scanner.nextLine();

                for(int z = 0; z < receptenBarman.length(); z++) {

                    // receptletter naar nummer vertaald;
                    int recept = receptenBarman.charAt(z) - 64;

                    aantalBarmannenPerRecept.get(y).add(recept);
                }
            }

            int aantalOrders = Integer.parseInt(scanner.nextLine());
            String mogelijkheidOrder = "mogelijk";

            for (int y = 0; y < aantalOrders; y++) {
                // make a new array with out results ^^

                // copy van vorige lijst;
                List<List<Integer>> aantalBeschikbareBarmannen = new ArrayList<>();
                String order = scanner.nextLine();

                for(int i = 0; i < aantalBarmannenPerRecept.size(); i++) {

                    // we add.() amount of barmannen
                    List<Integer> barman = new ArrayList<>();
                    aantalBeschikbareBarmannen.add(barman);
                    System.out.println(aantalBeschikbareBarmannen);

                    for(int j = 0; j < aantalBarmannenPerRecept.get(i).size(); j++) {

                        int huidigeRecept = aantalBarmannenPerRecept.get(i).get(j);
                        System.out.println("++" + huidigeRecept);
                        aantalBeschikbareBarmannen.get(i).add(huidigeRecept);

                        for(int z = 0; z < order.length(); z++) {

                            // van de letter naar nummer;
                            int karakterPositie = order.charAt(z) - 64;
                            System.out.println("--" + karakterPositie);

                            if(huidigeRecept == karakterPositie && aantalBarmannenPerRecept.get(i).get(j) > 0) {
                                aantalBeschikbareBarmannen.get(i).set(j, (~(aantalBarmannenPerRecept.get(i).get(j)-1)));
                                System.out.println("boop");
                                System.out.println("o::" + aantalBeschikbareBarmannen);
                            }
                        }
                    }
                }
                System.out.println("onder::" + aantalBeschikbareBarmannen);
            }
        }
    }
}
