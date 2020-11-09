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
            List<List<Integer>> kaartenInHand = new ArrayList<List<Integer>>();
            List<List<Integer>> kaartenMogelijkInHand = new ArrayList<List<Integer>>();
            List<List<Integer>> kaartenOnmogelijkInHand = new ArrayList<List<Integer>>();

            // maakt een list voor elke speler
            for (int x = 0; x < aantalSpelers; x++) {
                List<Integer> spelerMogelijk = new ArrayList<>();
                List<Integer> spelerOnmogelijk = new ArrayList<>();
                List<Integer> spelers = new ArrayList<>();
                kaartenMogelijkInHand.add(spelerMogelijk);
                kaartenOnmogelijkInHand.add(spelerOnmogelijk);
                kaartenInHand.add(spelers);
            }

            // per ronde steken wij de gevraade tot benatwoorder data in een lijst.
            for (int j = 0; j < aantalVragen; j++)  {
                String ronde = kaarten.bijhouden(reader.nextLine());
                rondes[j] = ronde;
            }
            kaarten.sorteren(aantalSpelers, aantalPersonen, aantalLocaties, aantalWapens, rondes, kaartenInHand, kaartenMogelijkInHand, kaartenOnmogelijkInHand);

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
        //System.out.println("aantalpersonen:" + aantalPersonen);
        //System.out.println("aantallocaties:" + aantalLocaties);
        //System.out.println("aantalWapens:" + aantalWapens);

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

    public void sorteren(int aantalSpelers, int aantalPersonen, int aantalLocaties, int aantalWapens, String[] rondes,List<List<Integer>> kaartenInHand, List<List<Integer>> kaartenMogelijkInHand, List<List<Integer>> kaartenOnmogelijkInHand) {

        // vraagRondes // elke ronde is data anders dus hier gebeurt veel
        for (int j = 0; j<rondes.length; j++) {

            int vrager = Character.getNumericValue(rondes[j].charAt(0));
            int gevraagdeKaarten = Integer.valueOf(rondes[j].substring(1, 4));
            int gevraagdeSpeler = Character.getNumericValue(rondes[j].charAt(4));

            // sla de gevraagdekaarten als "mogelijkinhand" op bij de vrager
            kaartenMogelijkInHand.get(vrager-1).add(gevraagdeKaarten);

            // als niemand kaarten heeft, dan zullen wij die alleen als "mogelijkinhand" opslaan voor de vrager
            if (Character.isDigit(rondes[j].charAt(4))) {
                // sla de kaarten in de gevraagdespeler zijn handen in
                kaartenInHand.get(gevraagdeSpeler-1).add(gevraagdeKaarten);

                if(j > 0) {
                    int vorigeGevraagdeSpeler;
                    int huidigeGevraagdeSpeler = Character.getNumericValue(rondes[j].charAt(4));
                    if(Character.isDigit(rondes[j-1].charAt(4))) {
                        vorigeGevraagdeSpeler = Character.getNumericValue(rondes[j-1].charAt(4));
                    } else {
                        vorigeGevraagdeSpeler = Character.getNumericValue(rondes[j-2].charAt(4));
                    }

                    if(vorigeGevraagdeSpeler + 1 == huidigeGevraagdeSpeler || vorigeGevraagdeSpeler == 0) {
                        // als geen skippers, mogelijke kaarten toevoegen bij de resterende twee behalve de vrager en beantwoordde
                        for(int i = 1; i <= aantalSpelers; i++) {
                            if(i != vrager && i != huidigeGevraagdeSpeler) {
                                kaartenMogelijkInHand.get(i-1).add(gevraagdeKaarten);
                            }
                        }

                    } else {
                        // als een ronde skippers bevat, zullen wij kaarten van die ronde voor de skippers op onmogelijk zetten en duplicates uit
                        // mogelijkehand verwijderen
                        //System.out.println("vorige:" + vorigeGevraagdeSpeler + "vrager:" + vrager + " - huid:" + huidigeGevraagdeSpeler + " - kaart:" + gevraagdeKaarten);
                        int x = 0;
                        //List<Integer> skippers = new ArrayList<Integer>();

                        while(x != 4) {
                            x++;

                            int volgendeSpeler = vorigeGevraagdeSpeler + x;

                            if(!(volgendeSpeler <= aantalSpelers)) {
                                volgendeSpeler -= 4;
                            }

                            // als ze gelijk zijn betekent het dat er geen skippers zijn
                            if(volgendeSpeler == huidigeGevraagdeSpeler) {

                                break;

                            } else if (volgendeSpeler != vrager ) {

                                if(kaartenMogelijkInHand.get(volgendeSpeler-1).contains(gevraagdeKaarten)) {
                                    kaartenMogelijkInHand.get(volgendeSpeler-1).remove(Integer.valueOf(gevraagdeKaarten));
                                }

                                //skippers.add(volgendeSpeler);

                                kaartenOnmogelijkInHand.get(volgendeSpeler-1).add(gevraagdeKaarten);
                            }

                        }
                        //System.out.println(skippers);
                    }
                }

            } else {
                for(int i = 1; i <= aantalSpelers; i++) {
                    if(i != vrager) {
                        kaartenOnmogelijkInHand.get(i-1).add(gevraagdeKaarten);
                    }
                }

            }

        }

        for (int x = 0;  x < aantalSpelers; x++) {

            for ( int y = 0; y < kaartenInHand.get(x).size(); y++) {

                for (int z = 0; z < kaartenOnmogelijkInHand.get(x).size(); z++) {

                    String temp = kaartenInHand.get(x).get(y).toString();
                    String temp_compare = kaartenOnmogelijkInHand.get(x).get(z).toString();

                    if (temp.charAt(1) == temp_compare.charAt(1) && temp.charAt(2) == temp_compare.charAt(2)  && temp.charAt(0) != temp_compare.charAt(0)) // if its not same it means it might exist
                    {
                        // hier numberhouden
                        System.out.println(temp);
                        System.out.println(temp_compare.charAt(0));
                        System.out.println("unique - first");
                    }
                    if (temp.charAt(1) == temp_compare.charAt(1) && temp.charAt(0) == temp_compare.charAt(0)  && temp.charAt(2) != temp_compare.charAt(2) ){ // if its not same it means it might exist
                        // hier grote alfabet
                        int b = (int) temp.charAt(2) + 48;
                        // hier numberhouden
                        System.out.println((char)b);
                        System.out.println("unique - last");
                        System.out.println(temp);
                    }
                    if (temp.charAt(2) == temp_compare.charAt(2) && temp.charAt(0) == temp_compare.charAt(0)  && temp.charAt(1) != temp_compare.charAt(1)) {
                        // hier klein kapitaal
                        int b = (int) temp.charAt(1) + 16;
                        // hier numberhouden
                        System.out.println((char)b);
                        System.out.println("unique - middle unique");
                        System.out.println(temp);
                    }
                    //System.out.println(temp + ":" + temp_compare);
                }
            }

            System.out.println("bij speler" + (x+1) + ":" + kaartenInHand.get(x));
            System.out.println("mogelijk bij speler" + (x+1) + ":" + kaartenMogelijkInHand.get(x));
            System.out.println("onmogelijk bij speler" + (x+1) + ":" + kaartenOnmogelijkInHand.get(x));
            // hierin roepen wij dan bewerken() ^^
        }

        // hier kunnen wij het doen, laat ons beginnen met speler 1

    }

    public void bewerken() {

    }

}