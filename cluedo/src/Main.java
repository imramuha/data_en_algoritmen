import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        System.out.println("Voer de input voor output in: ");

        //String[] Oplossingen = new String[aantalSpelen];

        int aantalSpelen = Integer.parseInt(reader.nextLine());
        final int aantalSpelers = 4;

        Speel speel = new Speel();
        Kaarten kaarten = new Kaarten();

        for(int i = 0; i < aantalSpelen; i++) {

            String aantalSoortenKaarten = reader.nextLine();
            int aantalVragen = Integer.parseInt(reader.nextLine());


            List<Character> aantalKaarten = kaarten.aantal(aantalSoortenKaarten);

            int aantalPersonen = Character.getNumericValue(aantalKaarten.get(0));
            int aantalLocaties = Character.getNumericValue(aantalKaarten.get(1));
            int aantalWapens = Character.getNumericValue(aantalKaarten.get(2));

            // opslaan en conversie van de input
            String rondes[] = new String[aantalVragen];
            List<List<Integer>> kaartenMogelijkInHand = new ArrayList<List<Integer>>();
            List<List<Integer>> kaartenOnmogelijkInHand = new ArrayList<List<Integer>>();

            // maakt een list voor elke speler
            for (int x = 0; x < aantalSpelers; x++) {
                List<Integer> spelerMogelijk = new ArrayList<>();
                List<Integer> spelerOnmogelijk = new ArrayList<>();
                kaartenMogelijkInHand.add(spelerMogelijk);
                kaartenOnmogelijkInHand.add(spelerOnmogelijk);
            }

            // per ronde steken wij de gevraade tot benatwoorder data in een lijst.
            for (int j = 0; j < aantalVragen; j++)  {
                String ronde = kaarten.bijhouden(reader.nextLine());
                rondes[j] = ronde;
            }
            kaarten.sorteren(aantalSpelers, aantalPersonen, aantalLocaties, aantalWapens, rondes, kaartenMogelijkInHand, kaartenOnmogelijkInHand);

            speel.speel(aantalSpelers, aantalPersonen, aantalLocaties, aantalWapens);

        }

        // bijwerken

        // speel*/

        //once finished
        reader.close();

  }
}

class Speel {

    // voor elke spel voer de vragenronde uit
    public void speel(int aantalSpelers, int aantalPersonen, int aantalLocaties, int aantalWapens) {
        System.out.println("aantalpersonen:" + aantalPersonen);
        System.out.println("aantallocaties:" + aantalLocaties);
        System.out.println("aantalWapens:" + aantalWapens);

        // aantal kaarten -1 (oplossing) worden over aantal spelers verdeeld
        int kaartenSpeler = (((aantalPersonen)-1) + (aantalLocaties-1) + ((aantalWapens)-1))/aantalSpelers;

        // hierin zullen wij de vragen overlopen

    }

    public void speelRondes (){

    }

}


class Kaarten {

    // uitvoeren zoveel keren als aantal games en de waardes per spel ook tonen!
    public List<Character> aantal (String aantalSoortenKaarten) {
        List<Character> list = new ArrayList<Character>();
        for (int i = 0; i < aantalSoortenKaarten.length(); i++){
            char c = aantalSoortenKaarten.charAt(i);
            if(c != ' ') {
                list.add(c);
            }
        }
        return list;
    }

    public String bijhouden(String line) {

           // soorten kaarten en conversies
           char vrager = line.charAt(0);
           char persoon = line.charAt(2);
           char locatie = line.charAt(3);
           int locatieCode = locatie - 'A' + 1;
           char wapen = line.charAt(4);
           int wapenCode = wapen - 'a' + 1;
           char gevraagde = line.charAt(6);

           // dit gebeurd per vraag

           String ronde = String.valueOf(vrager)+
                   String.valueOf(persoon)+
                   String.valueOf(locatieCode)+
                   String.valueOf(wapenCode)+
                   String.valueOf(gevraagde)+"\n";

           return ronde;
    }

    public void sorteren(int aantalSpelers, int aantalPersonen, int aantalLocaties, int aantalWapens, String[] rondes, List<List<Integer>> kaartenMogelijkInHand, List<List<Integer>> kaartenOnmogelijkInHand) {

        // vraagRondes // elke ronde is data anders dus hier gebeurt veel
        for (int j = 0; j<rondes.length; j++) {

            int vrager = Character.getNumericValue(rondes[j].charAt(0));
            int gevraagdeKaarten = Integer.valueOf(rondes[j].substring(1, 4));
            int gevraagdeSpeler = Character.getNumericValue(rondes[j].charAt(4));

            // sla de gevraagdekaarten als "mogelijkinhand" op bij de vrager
            kaartenMogelijkInHand.get(vrager-1).add(gevraagdeKaarten);

            // als niemand kaarten heeft, dan zullen wij die alleen als "mogelijkinhand" opslaan voor de vrager
            if (Character.isDigit(rondes[j].charAt(4))) {
                // sla de kaarten per speler op..
                kaartenMogelijkInHand.get(gevraagdeSpeler-1).add(gevraagdeKaarten);



                if(j > 0) {
                    int vorigeGevraagdeSpeler = rondes[j-1].charAt(4);
                    int huidigeGevraagdeSpeler = rondes[j].charAt(4);
                    int x = 1;

                    while(x <= 4) {
                        //System.out.println(x);

                        if(huidigeGevraagdeSpeler == aantalSpelers)
                        {
                            x = 1;
                        }
                        else {
                            x++;
                        }



                        // gaat vier keer gebeuren, we gaan de data in vier keren in de juiste gedoe steken
                        // wij gaan kijken of er skippers zijn
                        // vorige gevraagdespeler is bijgekomen met +1?
                        if(vorigeGevraagdeSpeler + 1 == huidigeGevraagdeSpeler) {
                            //System.out.println("plus met 1");
                            // de resterende twee spelers kregen deze kaart als mogelijk!!
                        } else {
                            // hier moeten wij de cijfers van de skippers uitkomen
                            // bij skippers zal deze kaart bij onmogelijk gestoken worden, bij de andere bij mogelijk
                            //System.out.println("plus met ???");
                        }

                    }

                }


            } else {
                // als X, wij steken de kaarten bij onmogelijk voor alle behalve vrager
                // nu moeten wij onmogelijke steken bij skippers!

                // dan gaan wij onmogelijke kaarten toevoegen bij de resterende drie behalve de vrager
                for(int i = 1; i <= aantalSpelers; i++) {
                    if(i != vrager) {
                        kaartenOnmogelijkInHand.get(i-1).add(gevraagdeKaarten);
                    }
                }

            }

        }



        // kaartenmogelijkinhand z??1
        for (int x = 0;  x < 1; x++) {
            // ik ga per 3 cifers zoeken of er zijn waarbij de 1ste letter een match is, dan ga ik kijken of de
            // sow elke mogelijke hand


            for ( int y = 0; y < kaartenMogelijkInHand.get(x).size(); y++) {
                System.out.println(kaartenMogelijkInHand.get(x).size());

                for (int z = 0; z < kaartenOnmogelijkInHand.get(x).size(); z++) {

                    // ever wanna delete duplicates beforehand
                    /*if(kaartenMogelijkInHand.get(x).get(y) == kaartenMogelijkInHand.get(x).get(y+1)) {
                        kaartenMogelijkInHand.get(x).remove(y);

                    }*/

                    System.out.println(kaartenOnmogelijkInHand.get(x).size());



                    // eerst gaan wij kijken in onmogelijk, als wel, dan naar volgende tot onmogelijk gedaan is
                    // daan doen wij voort met mogelijke
                    // dan weer met onmogelijke

                    int temp = kaartenMogelijkInHand.get(x).get(y);
                    int temp_compare = kaartenOnmogelijkInHand.get(x).get(z);

                    // remove same cards from kartenmgelijkinhand
                    System.out.println(temp);
                    System.out.println(temp_compare);

                }

            }

            for ( int y = 0; y < kaartenMogelijkInHand.get(x).size(); y++) {
                    System.out.println(y);

                for (int z = y+1; z < kaartenMogelijkInHand.get(x).size(); z++) {

                    // ever wanna delete duplicates beforehand
                    /*if(kaartenMogelijkInHand.get(x).get(y) == kaartenMogelijkInHand.get(x).get(y+1)) {
                        kaartenMogelijkInHand.get(x).remove(y);

                    }*/

                    System.out.println(z);



                    // eerst gaan wij kijken in onmogelijk, als wel, dan naar volgende tot onmogelijk gedaan is
                    // daan doen wij voort met mogelijke
                    // dan weer met onmogelijke

                    int temp = kaartenMogelijkInHand.get(x).get(y);
                    int temp_compare = kaartenMogelijkInHand.get(x).get(z);

                    // remove same cards from kartenmgelijkinhand
                    System.out.println(temp);
                    System.out.println(temp_compare);

                }

            }


            
            // tweede bij die een match is
            // dan de laatste, als ik een match vind waarbij de andere twee verschillend zijn:
            // zeker in de hand!!
            System.out.println("mogelijk bij speler" + (x+1) + ":" + kaartenMogelijkInHand.get(x));
            System.out.println("onmogelijk bij speler" + (x+1) + ":" + kaartenOnmogelijkInHand.get(x));
            // hierin roepen wij dan bewerken ^^
        }

        // hier kunnen wij het doen, laat ons beginnen met speler 1

    }

    public void bewerken() {

    }

}