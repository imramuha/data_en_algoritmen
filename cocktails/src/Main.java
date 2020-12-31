import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Bar bar = new Bar();
        bar.manage();

    }
}

class Bar {
    public void manage() {

        Scanner scanner = new Scanner(System.in);

        // 1-100
        int aantalOpgaven = Integer.parseInt(scanner.nextLine());


        for(int x = 0; x < aantalOpgaven; x++) {

            // 1-100
            int aantalBarmannen = Integer.parseInt(scanner.nextLine());
            List<List<Character>> aantalBarmannenPerRecept = new ArrayList<>();

            for (int y = 0; y < aantalBarmannen; y++) {

                List<Character> recepten = new ArrayList<>();
                aantalBarmannenPerRecept.add(recepten);

                // A-Z alfabetisch, hoofdletters en max 26, zonder spaties
                String receptenBarman = scanner.nextLine();


                String recept = receptenBarman;
                for(int z = 0; z < recept.length(); z++) {
                    aantalBarmannenPerRecept.get(y).add(recept.charAt(z));
                }

            }

            int aantalOrders = Integer.parseInt(scanner.nextLine());
            List<List<Character>> alleOrders = new ArrayList<>();

            int counter = 0;

            for (int y = 0; y < aantalOrders; y++) {

                List<Character> orders = new ArrayList<>();
                alleOrders.add(orders);

                String order = scanner.nextLine();
                for(int z = 0; z < order.length(); z++) {
                    alleOrders.get(y).add(order.charAt(z));
                }
            }

            List<String> resultaten = new ArrayList<>();
            serveer(aantalBarmannenPerRecept, alleOrders, counter, resultaten);
        }
    }

    public void serveer(List<List<Character>> aantalBarmannenPerRecept, List<List<Character>> alleOrders, int counter, List<String> resultaten) {

        // basecase
       if(counter == alleOrders.size()) {
           String order = "";

           for(int resultaat = 0; resultaat < resultaten.size() ; resultaat++) {

               for(int orderRecept = 0; orderRecept < alleOrders.get(resultaat).size(); orderRecept++) {
                   order += alleOrders.get(resultaat).get(orderRecept);
               }

               System.out.println( order + " " + resultaten.get(resultaat));
               order = "";
           }
           return;
       }

        List<String> resultaat = new ArrayList<>();
        int i = 0;      // index
        int j = 0;      // counter om te zien of er bij enige alle true waren, bij elke true +1 counter j
        List<Character> orders = alleOrders.get(counter);

        resultaat = mogelijkeCombinaties(aantalBarmannenPerRecept, i, orders, j, resultaat);

        if(controleResultaat(resultaat) == true) {
            resultaten.add("mogelijk");
        } else {
            resultaten.add("onmogelijk");
        }

        counter++;
        serveer(aantalBarmannenPerRecept, alleOrders , counter, resultaten);
    }

    // controle mogelijk;
    public Boolean controleResultaat(List<String> resultaat) {
        for(int x = 0; x < resultaat.size(); x++) {
            if(resultaat.contains("mogelijk")) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    // controleert alle combinaties tussen orders/barmannen
    static List<String> mogelijkeCombinaties(List<List<Character>> aantalBarmannenPerRecept, int k, List<Character> orders, int j, List<String> resultaat){

        for(int i = k; i < aantalBarmannenPerRecept.size(); i++){
            java.util.Collections.swap(aantalBarmannenPerRecept, i, k);
            mogelijkeCombinaties(aantalBarmannenPerRecept, k+1, orders, j, resultaat);
            java.util.Collections.swap(aantalBarmannenPerRecept, k, i);
        }
        if ( k == aantalBarmannenPerRecept.size() - 1 ){
            for(int y = 0; y < orders.size(); y++) {
                if(aantalBarmannenPerRecept.size() < orders.size()) {
                    break;
                }
                if(aantalBarmannenPerRecept.get(y).contains(orders.get(y))) {
                    j++;
                }
            }

            if (j == orders.size() ) {
                resultaat.add("mogelijk");
                return resultaat;
            } else if (j != orders.size()) {
                resultaat.add("onmogelijk");
            }
        }
        return resultaat;
    }

}
