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
        System.out.println(aantalOpgaven);


        for(int x = 0; x < aantalOpgaven; x++) {

            // 1-100
            int aantalBarmannen = Integer.parseInt(scanner.nextLine());
            System.out.println(aantalBarmannen);

            for (int y = 0; y < aantalBarmannen; y++) {

                // A-Z alfabetisch, hoofdletters en max 26, zonder spaties
                String receptenBarman = scanner.nextLine();

                System.out.println(receptenBarman);
            }

            // 1-100
            int aantalOrders = Integer.parseInt(scanner.nextLine());
            System.out.println(aantalOrders);

            for (int y = 0; y < aantalOrders; y++) {

                // A-Z alfabetisch, hoofdletters en max 26, zonder spaties
                String order = scanner.nextLine();
                System.out.println(order);
            }
        }

    }
}
