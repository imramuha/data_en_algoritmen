import java.util.ArrayList;
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

            int eliminatieStatus = 0;


            // opslaan en conversie van de input
            String rondes[] = new String[aantalVragen];
            List<List<String>> kaartenInHand = new ArrayList<List<String>>();
            List<List<String>> kaartenMogelijkInHand = new ArrayList<List<String>>();
            List<List<String>> kaartenOnmogelijkInHand = new ArrayList<List<String>>();


            // maakt een list voor elke speler
            for (int x = 0; x < aantalSpelers; x++) {
                List<String> spelerMogelijk = new ArrayList<>();
                List<String> spelerOnmogelijk = new ArrayList<>();
                List<String> spelers = new ArrayList<>();
                kaartenMogelijkInHand.add(spelerMogelijk);
                kaartenOnmogelijkInHand.add(spelerOnmogelijk);
                kaartenInHand.add(spelers);
            }

            // per ronde steken wij de gevraade tot benatwoorder data in een lijst.
            for (int j = 0; j < aantalVragen; j++)  {
                String ronde = kaarten.bijhouden(reader.nextLine());
                rondes[j] = ronde;
            }
            kaarten.sorteren(aantalSpelers, eliminatieStatus, aantalPersonen, aantalLocaties, aantalWapens, rondes, kaartenInHand, kaartenMogelijkInHand, kaartenOnmogelijkInHand);

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
        System.out.println(kaartenSpeler);

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

    public void sorteren(int aantalSpelers, int eliminatieStatus, int aantalPersonen, int aantalLocaties, int aantalWapens, String[] rondes,List<List<String>> kaartenInHand, List<List<String>> kaartenMogelijkInHand, List<List<String>> kaartenOnmogelijkInHand) {

        // vraagRondes // elke ronde is data anders dus hier gebeurt veel
        for (int j = 0; j<rondes.length; j++) {

            int vrager = Character.getNumericValue(rondes[j].charAt(0));
            String gevraagdeKaarten = rondes[j].substring(1, 4);
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

        List<List<String>> uniquesMogelijk = new ArrayList<List<String>>();
        List<List<String>> uniqueKaarten = new ArrayList<List<String>>();

        for (int x = 0;  x < aantalSpelers; x++) {

            List<String> spelersLists = new ArrayList<>();
            List<String> spelersHashSets = new ArrayList<>();
            uniquesMogelijk.add(spelersLists);
            uniqueKaarten.add(spelersHashSets);

           for ( int y = 0; y < kaartenInHand.get(x).size(); y++) {

               String temp = kaartenInHand.get(x).get(y);
               String t = "";
               String o = "";
               String m = "";

                for ( int z = 0; z < 3; z++) {

                    for (int xx = 0; xx < kaartenOnmogelijkInHand.get(x).size(); xx++) {
                        String temp_compare = kaartenOnmogelijkInHand.get(x).get(xx);


                        if (temp.charAt(z) != temp_compare.charAt(z) && z == 0) {
                            t =  t + temp.charAt(z);
                        } else if(z == 0) {
                            t =  t + "x";
                            break;
                        }

                        if (temp.charAt(z) != temp_compare.charAt(z) && z == 1) {

                            o =  o + temp.charAt(z);
                        } else if(z == 1) {
                            o =  o + "x";
                            break;
                        }

                        if (temp.charAt(z) != temp_compare.charAt(z) && z == 2) {
                            m =  m + temp.charAt(z);
                        } else if(z == 2) {
                            m =  m + "x";
                            break;
                        }

                    }
                }

               /*System.out.println("t: " +t);
               System.out.println("o: " +o);
               System.out.println("m: " + m);*/

                String ol = String.valueOf(t.charAt(t.length()-1)) + String.valueOf(o.charAt(o.length()-1)) + String.valueOf(m.charAt(m.length()-1));
                uniquesMogelijk.get(x).add(ol);

                int aantalOnmogelijkeKaarten = 0;

                for(int l = 0; l < 3; l++) {
                    if(Character.isAlphabetic(ol.charAt(l))){
                        aantalOnmogelijkeKaarten += 1;
                    }
                    if (aantalOnmogelijkeKaarten == 2) {
                        uniqueKaarten.get(x).add(ol);
                        // hier moet ik het verwijderen;
                        uniquesMogelijk.get(x).remove(ol);
                    }
                }

               if (Character.isAlphabetic(t.charAt(t.length()-1)) && Character.isAlphabetic(o.charAt(o.length()-1))) {
                   uniqueKaarten.get(x).add(String.valueOf(t.charAt(t.length()-1)) + String.valueOf(o.charAt(o.length()-1)) + String.valueOf(m.charAt(m.length()-1)));
               }
               if (Character.isAlphabetic(t.charAt(t.length()-1)) && Character.isAlphabetic(m.charAt(m.length()-1))) {
                   uniqueKaarten.get(x).add(String.valueOf(t.charAt(t.length()-1)) + String.valueOf(o.charAt(o.length()-1)) + String.valueOf(m.charAt(m.length()-1)));
               }
               if(Character.isAlphabetic(o.charAt(o.length()-1)) && Character.isAlphabetic(m.charAt(m.length()-1))) {
                   uniqueKaarten.get(x).add(String.valueOf(t.charAt(t.length()-1)) + String.valueOf(o.charAt(o.length()-1)) + String.valueOf(m.charAt(m.length()-1)));
               }
            }

            /*System.out.println("uniques nog mogelijk:" + uniquesMogelijk);
            System.out.println("uniques al gevonden:" + uniqueKaarten);
            System.out.println("bij speler" + (x+1) + ":" + kaartenInHand.get(x));
            System.out.println("onmogelijk bij speler:" + (x+1) + ":" + kaartenOnmogelijkInHand.get(x));
            System.out.println("---------------------");*/

        }

        bewerken(aantalSpelers, uniquesMogelijk, uniqueKaarten, eliminatieStatus);

    }

    public int elimineren(int aantalSpelers, List<List<String>> uniquesMogelijk, List<List<String>> uniqueKaarten) {
        // hierin gaan wij kaarten x'en/elimineren;

        //String temp = kaartenInHand.get(x).get(y);

        System.out.println("uniques nog mogelijk:" + uniquesMogelijk);
        System.out.println("uniques al gevonden:" + uniqueKaarten);
        int status = 0;

        for (int x = 0; x < aantalSpelers; x++) {

            for(int y = 0; y < uniquesMogelijk.get(x).size(); y++) {

                String uniqueMogelijk = uniquesMogelijk.get(x).get(y);
                String persoon = "";
                String locatie = "";
                String wapen = "";

                //System.out.println("yy" + uniquesMogelijk.get(x).size());

                for(int z = 0; z < aantalSpelers; z++) {

                        for (int xx = 0; xx < uniqueKaarten.get(z).size(); xx++) {
                            //System.out.println( "zz" + uniqueKaarten.get(z).size());

                            String unique = uniqueKaarten.get(z).get(xx);
                            System.out.println(uniqueMogelijk);
                            System.out.println(unique);


                            for (int yy = 0; yy < 3; yy++) {
                                if (uniqueMogelijk.charAt(yy) != unique.charAt(yy) && yy == 0 && String.valueOf(uniqueMogelijk.charAt(yy)) != "x") {
                                    persoon = persoon + uniqueMogelijk.charAt(yy);
                                } else if (yy == 0) {
                                    persoon = persoon + "x";
                                    break;
                                }

                                if (uniqueMogelijk.charAt(yy) != unique.charAt(yy) && yy == 1 && String.valueOf(uniqueMogelijk.charAt(yy)) != "x") {
                                    locatie = locatie + uniqueMogelijk.charAt(yy);
                                } else if (yy == 1) {
                                    locatie = locatie + "x";
                                    break;
                                }

                                if (uniqueMogelijk.charAt(yy) != unique.charAt(yy) && yy == 2 && String.valueOf(uniqueMogelijk.charAt(yy)) != "x") {
                                    wapen = wapen + uniqueMogelijk.charAt(yy);
                                } else if (yy == 2) {
                                    wapen = wapen + "x";
                                    break;
                                }
                            }

,
                            System.out.println("---------");
                            status = 1;
                        }
                }
                String ol = String.valueOf(persoon.charAt(persoon.length()-1)) + String.valueOf(locatie.charAt(locatie.length()-1)) + String.valueOf(wapen.charAt(wapen.length()-1));

                System.out.println("OLLLL+++++" + ol);
                int aantalOnmogelijkeKaarten = 0;

                for(int l = 0; l < 3; l++) {
                    if(Character.isAlphabetic(ol.charAt(l))){
                        aantalOnmogelijkeKaarten += 1;
                    }
                    if (aantalOnmogelijkeKaarten == 2) {
                        uniqueKaarten.get(x).add(ol);
                        // hier moet ik het verwijderen;
                        uniquesMogelijk.get(x).remove(ol);
                    }
                }

                if (Character.isAlphabetic(persoon.charAt(persoon.length()-1)) && Character.isAlphabetic(locatie.charAt(locatie.length()-1))) {
                    uniqueKaarten.get(x).add(String.valueOf(persoon.charAt(persoon.length()-1)) + String.valueOf(locatie.charAt(locatie.length()-1)) + String.valueOf(wapen.charAt(wapen.length()-1)));
                }
                if (Character.isAlphabetic(persoon.charAt(persoon.length()-1)) && Character.isAlphabetic(wapen.charAt(wapen.length()-1))) {
                    uniqueKaarten.get(x).add(String.valueOf(persoon.charAt(persoon.length()-1)) + String.valueOf(locatie.charAt(locatie.length()-1)) + String.valueOf(wapen.charAt(wapen.length()-1)));
                }
                if(Character.isAlphabetic(locatie.charAt(locatie.length()-1)) && Character.isAlphabetic(wapen.charAt(wapen.length()-1))) {
                    uniqueKaarten.get(x).add(String.valueOf(persoon.charAt(persoon.length()-1)) + String.valueOf(locatie.charAt(locatie.length()-1)) + String.valueOf(wapen.charAt(wapen.length()-1)));
                }

                System.out.println("uniques nog mogelijk:" + uniquesMogelijk);
                System.out.println("uniques al gevonden:" + uniqueKaarten);
                System.out.println("---------------------");

            }


        }
        return status;
    }

    public void bewerken(int aantalSpelers, List<List<String>> uniquesMogelijk, List<List<String>> uniqueKaarten, int eliminatieStatus) {

        System.out.println(eliminatieStatus);
        // hier gaat een int zijn die ofwel 0 of 1 zal zijn, 1 als wij een nique zullen vinden, dan zal deze opnieuw herhaald worden;
        // als 0 dan hebben wij alle uniques gevonden dus ook geen dinges meer nodig;

        eliminatieStatus = elimineren(aantalSpelers, uniquesMogelijk, uniqueKaarten);

        System.out.println("::" + eliminatieStatus);

        // if returns 1 then we continue if 0 we stop;

        // w doen het tot de ize iet meer groeit
        // runs and fines nqies
        // converts
        // takes uniques data from eash player saves in a global var
    }

}