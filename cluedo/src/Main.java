import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);

        int aantalSpelen = Integer.parseInt(reader.nextLine());
        final int aantalSpelers = 4;

        Kaarten kaarten = new Kaarten();

        for(int i = 0; i < aantalSpelen; i++) {

            String resultaat;
            String aantalSoortenKaarten = reader.nextLine();
            int aantalVragen = Integer.parseInt(reader.nextLine());

            List<Character> aantalKaarten = kaarten.aantal(aantalSoortenKaarten);

            int aantalPersonen = Character.getNumericValue(aantalKaarten.get(0));
            int aantalLocaties = Character.getNumericValue(aantalKaarten.get(1));
            int aantalWapens = Character.getNumericValue(aantalKaarten.get(2));

            // speler mag alleen zoveel kaarten hebben!
            int kaartenPerSpeler = (((aantalPersonen)-1) + (aantalLocaties-1) + ((aantalWapens)-1))/aantalSpelers;

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

            resultaat = kaarten.sorteren(aantalSpelers, kaartenPerSpeler, rondes, kaartenInHand, kaartenMogelijkInHand, kaartenOnmogelijkInHand);
            System.out.println((i + 1)  + " " + resultaat);
        }
        reader.close();
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

    public String sorteren(int aantalSpelers, int kaartenPerSpeler, String[] rondes,List<List<String>> kaartenInHand, List<List<String>> kaartenMogelijkInHand, List<List<String>> kaartenOnmogelijkInHand) {
        // vraagRondes // elke ronde is data anders dus hier gebeurt veel
        for (int j = 0; j < rondes.length; j++) {

            int vrager = Character.getNumericValue(rondes[j].charAt(0));
            String gevraagdeKaarten = rondes[j].substring(1, 4);
            int gevraagdeSpeler = Character.getNumericValue(rondes[j].charAt(4));

            // sla de gevraagdekaarten als "mogelijkinhand" op bij de vrager
            kaartenMogelijkInHand.get(vrager-1).add(gevraagdeKaarten);

            // als niemand kaarten heeft, dan zullen wij die alleen als "mogelijkinhand" opslaan voor de vrager
            if (Character.isDigit(rondes[j].charAt(4))) {
                // sla de kaarten in de gevraagdespeler zijn handen in
                kaartenInHand.get(gevraagdeSpeler-1).add(gevraagdeKaarten);

                if(j >= 0) {

                    int huidigeGevraagdeSpeler = Character.getNumericValue(rondes[j].charAt(4));

                    int x = 0;

                    while(x != 4) {
                        x++;

                        // als geen skippers, mogelijke kaarten toevoegen bij de resterende twee behalve de vrager en huidige en
                        // igv x... iedereen behalve vrager
                        if(vrager + 1 == huidigeGevraagdeSpeler && huidigeGevraagdeSpeler == 'x') {
                            for(int i = 1; i <= aantalSpelers; i++) {
                                if(i != vrager && i != huidigeGevraagdeSpeler) {
                                    kaartenMogelijkInHand.get(i-1).add(gevraagdeKaarten);
                                }
                            }

                            // als er skippers zijn
                        } else {

                            int volgendeSpeler = vrager + x;

                            if(!(volgendeSpeler <= aantalSpelers)) {
                                volgendeSpeler -= 4;
                            }

                            if(volgendeSpeler == huidigeGevraagdeSpeler || volgendeSpeler == vrager) {
                                break;
                            }

                            if (huidigeGevraagdeSpeler != volgendeSpeler  && vrager  != volgendeSpeler ) {
                                if(kaartenMogelijkInHand.get(volgendeSpeler-1).contains(gevraagdeKaarten)) {
                                    kaartenMogelijkInHand.get(volgendeSpeler-1).remove(Integer.valueOf(gevraagdeKaarten));
                                }
                                kaartenOnmogelijkInHand.get(volgendeSpeler - 1).add(gevraagdeKaarten);
                            }
                        }
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

        List<HashSet<String>> uniquesMogelijk = new ArrayList<>();
        List<HashSet<Character>> uniqueKaarten = new ArrayList<>();

        for (int x = 0;  x < aantalSpelers; x++) {

            HashSet<String> spelersLists = new HashSet<>();
            uniquesMogelijk.add(spelersLists);
            HashSet<Character> spelersHashSets = new HashSet<>();
            uniqueKaarten.add(spelersHashSets);

            for ( int y = 0; y < kaartenInHand.get(x).size(); y++) {

               String temp = kaartenInHand.get(x).get(y);

               String persoonKaart = " ";
               String locatieKaart = " ";
               String wapenKaart = " ";

                for ( int z = 0; z < 3; z++) {

                    for (int xx = 0; xx < kaartenOnmogelijkInHand.get(x).size(); xx++) {

                        String temp_compare = kaartenOnmogelijkInHand.get(x).get(xx);

                        if (temp.charAt(z) != temp_compare.charAt(z) && z == 0) {
                            persoonKaart =  persoonKaart + temp.charAt(z);
                        } else if(z == 0) {
                            persoonKaart =  persoonKaart + "x";
                            break;
                        }

                        if (temp.charAt(z) != temp_compare.charAt(z) && z == 1) {
                            locatieKaart =  locatieKaart + temp.charAt(z);
                        } else if(z == 1) {
                            locatieKaart =  locatieKaart + "x";
                            break;
                        }

                        if (temp.charAt(z) != temp_compare.charAt(z) && z == 2) {
                            wapenKaart =  wapenKaart + temp.charAt(z);
                        } else if(z == 2) {
                            wapenKaart =  wapenKaart + "x";
                            break;
                        }
                    }
                }

                String kaartenCombinatie = String.valueOf(persoonKaart.charAt(persoonKaart.length()-1)) + String.valueOf(locatieKaart.charAt(locatieKaart.length()-1)) + String.valueOf(wapenKaart.charAt(wapenKaart.length()-1));

                int aantalOnmogelijkeKaarten = 0;

                for(int l = 0; l < 3; l++) {
                    if(Character.isAlphabetic(kaartenCombinatie.charAt(l))){
                        aantalOnmogelijkeKaarten += 1;
                    }
                }

                if (aantalOnmogelijkeKaarten == 0) {
                    uniquesMogelijk.get(x).add(temp);
                } else if(aantalOnmogelijkeKaarten == 1) {
                    uniquesMogelijk.get(x).add(kaartenCombinatie);
                } if (aantalOnmogelijkeKaarten == 2) {

                    for(int letter = 0; letter < kaartenCombinatie.length(); letter++) {

                        if(!Character.isAlphabetic(kaartenCombinatie.charAt(letter))) {

                            if(letter == 0) {
                                uniqueKaarten.get(x).add(kaartenCombinatie.charAt(letter));
                                uniquesMogelijk = schrap(x, letter, uniquesMogelijk, kaartenCombinatie.charAt(letter));

                            } else if (letter == 1) {
                                uniqueKaarten.get(x).add((char)(kaartenCombinatie.charAt(letter) + 16));
                                uniquesMogelijk = schrap(x, letter, uniquesMogelijk, kaartenCombinatie.charAt(letter));

                            } else if (letter == 2) {
                                uniqueKaarten.get(x).add((char)(kaartenCombinatie.charAt(letter) + 48));
                                uniquesMogelijk  = schrap(x, letter, uniquesMogelijk, kaartenCombinatie.charAt(letter));
                            }
                        }
                    }
                    uniquesMogelijk.get(x).remove(kaartenCombinatie);
                }
            }
        }

        // status voor de recursie
        int status = 0;
        filtering(status, kaartenPerSpeler, uniquesMogelijk, uniqueKaarten);
        return output(uniqueKaarten);
    }

    // filtering van kaarten nadat de sortering gedaan is.
    public Boolean filtering(int status, int kaartenPerSpeler, List<HashSet<String>> uniquesMogelijk, List<HashSet<Character>> uniqueKaarten) {

        if(status == 4) {
            return false;
        }

        for(int spelers = 0; spelers < uniquesMogelijk.size(); spelers++) {

            List<String> remove = new ArrayList<>();
            Iterator value = uniquesMogelijk.get(spelers).iterator();
            int geschrapteCounter = 0;
            Character persoonKaart = ' ';
            Character locatieKaart = ' ';
            Character wapenKaart = ' ';

            while (value.hasNext()) {
                String str = value.next().toString();

                for (int z = 0; z < 3; z++) {

                    if (z == 0) {

                        if (str.charAt(z) == 'x') {
                            geschrapteCounter += 1;
                            persoonKaart = 'x';

                        } else {
                            for (int y = 0; y < uniqueKaarten.size(); y++) {
                                if (uniqueKaarten.get(y).contains(str.charAt(z))) {
                                    geschrapteCounter  += 1;
                                    persoonKaart = 'x';
                                    break;
                                } else {
                                    persoonKaart = str.charAt(z);
                                }
                            }
                        }

                    } else if (z == 1) {

                        if (str.charAt(z) == 'x') {
                            geschrapteCounter  += 1;
                            locatieKaart = 'x';
                        } else {
                            for (int y = 0; y < uniqueKaarten.size(); y++) {
                                if (uniqueKaarten.get(y).contains((char) (str.charAt(z) + 16))) {
                                    geschrapteCounter  += 1;
                                    locatieKaart = 'x';
                                    break;
                                } else {
                                    locatieKaart = (char) (str.charAt(z) + 16);
                                }
                            }
                        }

                    } else if (z == 2) {
                        if (str.charAt(z) == 'x') {
                            geschrapteCounter  += 1;
                            wapenKaart = 'x';
                        } else {
                            for (int y = 0; y < uniqueKaarten.size(); y++) {
                                if (uniqueKaarten.get(y).contains((char) (str.charAt(z) + 48))) {
                                    geschrapteCounter  += 1;
                                    wapenKaart = 'x';
                                    break;
                                } else {
                                    wapenKaart = (char) (str.charAt(z) + 48);
                                }
                            }
                        }
                    }
                }

                if (geschrapteCounter == 2 && uniqueKaarten.get(spelers).size() != kaartenPerSpeler) {
                    uniqueKaarten.get(spelers).add(controleGeschrapteKaart(persoonKaart, locatieKaart, wapenKaart));

                    // als een unique gevonden is moeten bij andere nog mogelijke x's geplaatst worden bij dezelfde
                    // nog mogelijke unique
                    remove.add(str);

                } else if (geschrapteCounter == 3 ) {
                   remove.add(str);
                }
                geschrapteCounter = 0;
            }
            uniquesMogelijk.get(spelers).removeAll(remove);

            if (uniqueKaarten.get(spelers).size() == kaartenPerSpeler) {
                uniquesMogelijk.get(spelers).removeAll(uniquesMogelijk.get(spelers));
            }
        }

        // update status voor de recursie
        status = controleAantalspelersKaarten(uniqueKaarten, kaartenPerSpeler);

        // recursie
        filtering(status, kaartenPerSpeler, uniquesMogelijk, uniqueKaarten);
        return true;
    }

    // controleren voor gescrapte/x kaarten
    public char controleGeschrapteKaart (char persoon, char locatie, char wapen) {
        Character kaart = ' ';

        if(persoon != 'x') {
            kaart = persoon;
        } else if (locatie != 'x') {
            kaart = locatie;
        } else if (wapen != 'x') {
            kaart = wapen;
        }

        return kaart;
    }

    // kijken of de aantalkaarten per speler voor elke speler voldaan is.
    public int controleAantalspelersKaarten(List<HashSet<Character>> uniqueKaarten, int kaartenPerSpeler) {

        int status = 0;

        for(int x = 0; x < uniqueKaarten.size(); x++) {
            if(uniqueKaarten.get(x).size() == kaartenPerSpeler ) {
               status += 1;
            }
        }

        return status;
    }

    // uniques die wij na eerste sortering hebben, bekijken en schrappen wat al gevonden is.
    public List<HashSet<String>> schrap(int speler, int letter, List<HashSet<String>> uniquesMogelijk, char teSchrappenLetter) {

        Iterator kaarten = uniquesMogelijk.get(speler).iterator();
        ArrayList<String> teSchrappenKaarten = new ArrayList<>();
        ArrayList<String> geschrapteKaarten = new ArrayList<>();

        for(int x = 0; x < uniquesMogelijk.get(speler).size(); x++) {

            String kaart = kaarten.next().toString();
            String nieuweKaart = " ";

            if(kaart.charAt(letter) == teSchrappenLetter) {
                teSchrappenKaarten.add(kaart);

                if(letter == 0) {
                    nieuweKaart =  "x" + kaart.charAt(1) + kaart.charAt(2);
                } else if (letter == 1) {
                    nieuweKaart =  kaart.charAt(0) + "x"  + kaart.charAt(2);
                } else if (letter == 2) {
                    nieuweKaart =  kaart.charAt(1) + kaart.charAt(2) + "x";
                }
                geschrapteKaarten.add(nieuweKaart);
            }
        }

        uniquesMogelijk.get(speler).addAll(geschrapteKaarten);
        uniquesMogelijk.get(speler).removeAll(teSchrappenKaarten);

        return uniquesMogelijk;
    }

    public String output(List<HashSet<Character>> uniqueKaarten) {
        String resultaat = " ";

        for(int aantalSpelers = 0; aantalSpelers < uniqueKaarten.size(); aantalSpelers++) {
            ArrayList<Character> kaarten = new ArrayList<>(uniqueKaarten.get(aantalSpelers));
            Collections.sort(kaarten);

            for(int x = 0; x < kaarten.size(); x++) {
                resultaat = resultaat + kaarten.get(x);
            }
            resultaat += " ";
        }
        return resultaat;
    }
}