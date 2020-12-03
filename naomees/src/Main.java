import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int aantalTestGevallen = Integer.parseInt(reader.readLine());
        int aantalRegelsPerTestGeval = 5;

        List<String> alleResultaten = Arrays.asList(new String[aantalTestGevallen]);

        Controleer controleer = new Controleer();

        // per testgeval elk woord controleren;
        for(int i = 0; i < aantalTestGevallen; i++) {
            for (int j = 0; j < aantalRegelsPerTestGeval; j++) {

                String testGeval = reader.readLine();
                String temp = alleResultaten.get(i);

                if(temp == null) {
                    temp = controleer.testGeval(testGeval);
                } else {
                    temp = temp + " " + controleer.testGeval(testGeval);
                }
                alleResultaten.set(i, temp);
            }
        }

        // resultaten tonen;
        for(int i = 0; i < alleResultaten.size() ; i++) {
            System.out.println((i + 1) + " " + alleResultaten.get(i));
        }
    }
}

class Controleer {

    public String testGeval(String testGeval) {

        int aantalKoppelsPerTestGeval = testGeval.length() / 2;
        int aantalMogelijkeControles;

        String woordStatus = "naomees";

        // even of oneven aantal woordkoppels
        if(aantalKoppelsPerTestGeval % 2 == 0) {
            aantalMogelijkeControles = aantalKoppelsPerTestGeval / 2;
        } else {
            aantalMogelijkeControles = (aantalKoppelsPerTestGeval - 1) / 2;
        }

        int startBeginWoord = 0;
        int endEindeWoord = testGeval.length();

        for(int x = aantalMogelijkeControles; x > 0; x--) {

            String beginWoord = testGeval.substring(startBeginWoord, (startBeginWoord + 2));
            String eindeWoord = testGeval.substring((endEindeWoord - 2), endEindeWoord);

            // vergelijken van woorden;
            if (!beginWoord.equals(eindeWoord)) {
                woordStatus = "onzin";
                break;
            }

            startBeginWoord = startBeginWoord + 2;
            endEindeWoord = endEindeWoord - 2;
        }

        return woordStatus;
    }


}
