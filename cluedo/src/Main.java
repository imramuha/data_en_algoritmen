import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);  // Reading from System.in

        System.out.println("Voer de input voor output in: ");

        int aantalSpelen = Integer.valueOf(reader.nextLine());
        int aantalSpelers = 4;

        // ["A", "B", "C", "D", "E", "F", "G", "H", "I"]
        // ["a", "b", "c", "d", "e", "f", "g", "h", "i"]
        // ["1", "2", "3", "4", "5", "6", "7", "8", "9"]

        String aantalSoortenKaarten = reader.nextLine();

        Kaarten kaarten = new Kaarten();


        List<Character> gesorteerdeKaarten = kaarten.sorteren(aantalSoortenKaarten);
        String aantalPersonen = String.valueOf(gesorteerdeKaarten.get(0));
        String aantalLocaties = String.valueOf(gesorteerdeKaarten.get(1));
        String aantalWapens = String.valueOf(gesorteerdeKaarten.get(2));

        // aantal vragen
        int aantalVragen = Integer.valueOf(reader.nextLine());

        System.out.println("aantalspelen:" + aantalSpelen);
        System.out.println("aantalPersonen:" + aantalPersonen);
        System.out.println("aantalLocaties:" + aantalLocaties);
        System.out.println("aantalWapens:" + aantalWapens);
        System.out.println("aantalVragen:" + aantalVragen);

        // daarna gaan wij aantal vragen keren onze input bekijken en opnieuw onze waardes herhalen
        Speel speel = new Speel();
        speel.speel(aantalSpelen, aantalVragen);


        while(reader.hasNextLine()){

        }

        //once finished
        reader.close();

  }
}

class Kaarten {

    public List<Character> sorteren (String aantalSoortenKaarten) {
        List<Character> list = new ArrayList<Character>();
        for (int i = 0; i < aantalSoortenKaarten.length(); i++){
            char c = aantalSoortenKaarten.charAt(i);
            if(c != ' ') {
                list.add(c);
            }
        }
        return list;
    }
}

class Speel {

    // voor elke spel voer de vragenronde uit
    public void speel(int aantalSpelen, int aantalVragen) {
        for (int i = 0; i < aantalSpelen; i++){
            vraag(aantalVragen);
        }
    }

    public void vraag(int aantalVragen) {
        // voor de vraag ronde moeten wij onze kaarten sorteren!! --> onze waardes resetten, enzo..
        // hier waarschijnlijk ont kaartsorteer methode roepen??

        for (int i = 0; i < aantalVragen; i++){
            System.out.println(i + "vraag");
        }

        // eens dit afgerond is, moeten wij resultaat tonen en alle andere waardes terug op 0 zetten;
        // hier de functie opnieuw overlopen met aantal vragen
    }
}

// String input = "6 4 4 3 16 1 1Aa 22 2Db 43 4Ab 24 4Ac 21 1Cb X2 4Cb 33 3Cc 44 2Bb X1 2Da 22 3Ac 33 2Aa 24 3Cb 11 3Dc 32 4Ba 33 1Aa 14 2Dc X2 3 2 8 1 2Aa X2 2Ab 43 2Ab 44 2Bb 11 1Ab 32 1Aa 33 1Ca 24 2Ca 22 3 2 8 1 1Ca 32 1Aa 43 1Ab X4 1Cb 31 2Ba 22 2Aa 43 1Ba 44 2Cb 12 3 2 12 1 1Ca 32 2Aa 13 2Ca 14 2Cb 31 2Ca 32 1Aa 43 1Aa 44 1Ab X1 1Bb 22 2Ba 13 2Aa 14 1Cb 38 6 5 32 1 2Da 22 2Cc 43 7Ce 44 8Bd 11 3Fc 32 6Bc 43 1Ca 44 8Ee 21 6Ea 42 1Be 13 1Bd 14 3Aa 31 5Dd 22 5Ce 43 2Fb 14 3Dd 21 8Fa 32 8Da 33 1Ae 44 4Fc 31 8De 22 1Fe 13 2Cd 44 5Ab 21 2Bd 32 6Ba 13 6Dd 14 5Cc X1 7Ec 22 6Ad 33 6Dc 44 6Cc 17 9 7 40 1 2Ea 32 6Ae 43 7Ig X4 6Ge 21 2Cf 22 7Ce 33 7Ce 14 2Id 21 1Hf 22 2Fd 43 5Ca 44 7Hg 21 7He 22 3Ab X3 1Fa 44 7Gc 11 2Fc 42 2Ig 33 1Da 44 1Ed 21 2Aa 22 1Cd 33 1Bb 14 2Ff 21 5Fe 22 7Bf 33 3Aa 14 7Db 31 6Cc 42 5Ia 33 4Hg 14 6Ba 11 1Ie 22 6Cg 33 2Da 44 1Gb 31 6Ie 22 4Gc 13 2Hc 44 3Be 1";

// invoer
/*
6 4 4 3 16 1 1Aa 22 2Db 43 4Ab 24 4Ac 21 1Cb X2 4Cb 33 3Cc 44 2Bb X1 2Da 22 3Ac 33 2Aa 24 3Cb 11 3Dc 32 4Ba 33 1Aa 14 2Dc X
2 3 2 8 1 2Aa X2 2Ab 43 2Ab 44 2Bb 11 1Ab 32 1Aa 33 1Ca 24 2Ca 2
2 3 2 8 1 1Ca 32 1Aa 43 1Ab X4 1Cb 31 2Ba 22 2Aa 43 1Ba 44 2Cb 1
2 3 2 12 1 1Ca 32 2Aa 13 2Ca 14 2Cb 31 2Ca 32 1Aa 43 1Aa 44 1Ab X1 1Bb 22 2Ba 13 2Aa 14 1Cb 3
8 6 5 32 1 2Da 22 2Cc 43 7Ce 44 8Bd 11 3Fc 32 6Bc 43 1Ca 44 8Ee 21 6Ea 42 1Be 13 1Bd 14 3Aa 31 5Dd 22 5Ce 43 2Fb 14 3Dd 21 8Fa 32 8Da 33 1Ae 44 4Fc 31 8De 22 1Fe 13 2Cd 44 5Ab 21 2Bd 32 6Ba 13 6Dd 14 5Cc X1 7Ec 22 6Ad 33 6Dc 44 6Cc 1
7 9 7 40 1 2Ea 32 6Ae 43 7Ig X4 6Ge 21 2Cf 22 7Ce 33 7Ce 14 2Id 21 1Hf 22 2Fd 43 5Ca 44 7Hg 21 7He 22 3Ab X3 1Fa 44 7Gc 11 2Fc 42 2Ig 33 1Da 44 1Ed 21 2Aa 22 1Cd 33 1Bb 14 2Ff 21 5Fe 22 7Bf 33 3Aa 14 7Db 31 6Cc 42 5Ia 33 4Hg 14 6Ba 11 1Ie 22 6Cg 33 2Da 44 1Gb 31 6Ie 22 4Gc 13 2Hc 44 3Be 1
 */