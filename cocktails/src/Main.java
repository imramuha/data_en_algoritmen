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

            //System.out.println(aantalBarmannenPerRecept);

            int aantalOrders = Integer.parseInt(scanner.nextLine());
            String orderStatus = "";

            for (int y = 0; y < aantalOrders; y++) {

                String order = scanner.nextLine();
                serveer(aantalBarmannenPerRecept, order, orderStatus, 0, 1);



            }
        }
    }

    public Boolean serveer(List<List<Integer>> aantalBarmannenPerRecept, String order, String orderStatus, int opposum, int count) {

        // een oplossing gevonden //basecase
        if(orderStatus == "mogelijk") {
            System.out.println("mogelijk");
            return true;
        } else if(orderStatus == "onmogelijk") {
            System.out.println("onmogelijk");
            return true;
        }


        for(int x = opposum; x < aantalBarmannenPerRecept.size(); x++) {
            for(int y = x; y < order.length(); y++) {
                int karakterPositie = order.charAt(y) - 64;

                System.out.println("x" + x + ": " + aantalBarmannenPerRecept.get(x));
                System.out.println("y" + y + ": " + order.charAt(y) + " - " + (order.charAt(y) - 64));
                if(aantalBarmannenPerRecept.get(x).contains(karakterPositie)) {

                    System.out.println("count" + count);
                    System.out.println("siz" + (aantalBarmannenPerRecept.size()));

                    if(x == aantalBarmannenPerRecept.size()) {
                        System.out.println(1);
                        if (count == aantalBarmannenPerRecept.size()){
                            opposum += 1;
                            orderStatus = "mogelijk";
                            serveer(aantalBarmannenPerRecept, order, orderStatus, opposum, count);
                            break;
                        }
                        System.out.println(11);
                        opposum = 0;
                        count += 1;
                        serveer(aantalBarmannenPerRecept, order, orderStatus, opposum, count);
                        //break;

                    } else if (x != aantalBarmannenPerRecept.size()) {
                        System.out.println(2);
                        if (count == aantalBarmannenPerRecept.size()){
                            opposum += 1;
                            orderStatus = "mogelijk";
                            serveer(aantalBarmannenPerRecept, order, orderStatus, opposum, count);
                            break;
                        }
                        System.out.println(22);
                        opposum += 1;
                        count += 1;
                        serveer(aantalBarmannenPerRecept, order, orderStatus, opposum, count);
                        break;
                    }

                } else {

                    // ERGENS MOETEN WIJ ACHTERHALEN OM DIE TE STOPPEN TE RECURSIEVEN

                    if(x == aantalBarmannenPerRecept.size()) {
                        System.out.println(4);
                        opposum = 0;
                        count += 1;
                        serveer(aantalBarmannenPerRecept, order, orderStatus, opposum, count);
                    } else if (x != aantalBarmannenPerRecept.size() ) {
                        System.out.println(5);
                        opposum += 1;
                        count += 1;
                        serveer(aantalBarmannenPerRecept, order, orderStatus, opposum, count);
                    } else if (count == aantalBarmannenPerRecept.size()) {
                        System.out.println(6);
                        count = 0;
                        orderStatus = "onmogelijk";
                        serveer(aantalBarmannenPerRecept, order, orderStatus, opposum, count);
                        break;
                    }
                }
            }
            System.out.println("---");
        }
        return false;
    }
}
