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
        System.out.println(aantalZinnen);

        for(int x = 0; x < aantalZinnen; x++) {
            String ingediendeZin = scanner.nextLine();
            String correcteZin = scanner.nextLine();

            System.out.println(ingediendeZin);
            System.out.println(correcteZin);
        }



    }
}
