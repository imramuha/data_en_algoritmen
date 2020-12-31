import java.util.ArrayList;
        import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Achtbaan achtbaan = new Achtbaan();
        achtbaan.construeer();

    }
}

class Achtbaan {

    public void construeer() {

        Scanner scanner = new Scanner(System.in);
        int aantalAchtbanen = Integer.parseInt(scanner.nextLine());

        for(int i = 0; i < aantalAchtbanen; i++) {

            int x = 0;
            int y = 0;
            int z = 0;
            String richting = "O";

            int aantalStappen = scanner.nextInt();
            String stappen = scanner.next();

            ArrayList<ArrayList<Character>> maxVerdiepingen = new ArrayList<ArrayList<Character>>(aantalStappen);
            ArrayList<Character> maxBewegingen = new ArrayList<Character>(aantalStappen);
            maxVerdiepingen.add(maxBewegingen);

            for(int j = 0; j < aantalStappen;j++) {
                ArrayList<Character> segment = new ArrayList<Character>(aantalStappen);

                switch(stappen.charAt(j)) {
                    case 'S': {
                        System.out.println("S: " + x);
                        maxVerdiepingen.get(y).add('=');
                        x+=1;

                        break;
                    }
                    case 'V': {
                        System.out.println("V: "+ x);
                        if(richting == "O") {
                            if(x > maxVerdiepingen.get(y).size() - 1 ) {
                                maxVerdiepingen.get(y).add('_');

                                for(int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if(y != a) {
                                        maxVerdiepingen.get(a).add('.');
                                    }
                                }

                            } else if(x < 0) {

                                maxVerdiepingen.get(y).add(0, '_');

                                for(int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if(y != a) {
                                        maxVerdiepingen.get(a).add(0,'.');
                                    }
                                }
                            } else if (x < maxVerdiepingen.get(y).size() && x >= 0) {

                                // als z hoger is
                                // naar z toe is -
                                if (z <= 0 || maxVerdiepingen.get(y).get(x) == '.') {
                                    maxVerdiepingen.get(y).set(x, '_');
                                }
                            }

                            x+=1;
                        }

                        if (richting == "N") {
                            z+=1;
                        }

                        if (richting == "W") {
                            System.out.println("ezez" + z);

                            if(x > maxVerdiepingen.get(y).size() - 1 ) {
                                maxVerdiepingen.get(y).add('_');

                                for(int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if(y != a) {
                                        maxVerdiepingen.get(a).add('.');
                                    }
                                }

                            } else if(x < 0) {

                                maxVerdiepingen.get(y).add(0, '_');

                                for(int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if(y != a) {
                                        maxVerdiepingen.get(a).add(0,'.');
                                    }
                                }
                            } else if (x < maxVerdiepingen.get(y).size() && x >= 0) {

                                if (z <= 0 || maxVerdiepingen.get(y).get(x) == '.') {
                                    maxVerdiepingen.get(y).set(x,'_');
                                }
                            }

                            x-=1;

                        }
                        if(richting == "Z") {

                            if(x < 0 ) {
                                maxVerdiepingen.get(y).add(0,'_');

                                for(int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if(y != a) {
                                        maxVerdiepingen.get(a).add(0,'.');
                                    }
                                }


                            } else if (x < maxVerdiepingen.get(y).size() && x >= 0) {

                                if (z <= 0 || maxVerdiepingen.get(y).get(x) == '.') {
                                    maxVerdiepingen.get(y).set(x, '_');
                                }
                            }

                            z-=1;
                        }

                        break;
                    }
                    case 'U': {
                        System.out.println("U:"+ x);
                        if(y == 0) {
                            maxVerdiepingen.add(0, segment);

                            for(int a = 0; a < maxVerdiepingen.get(y+1).size(); a++) {
                                maxVerdiepingen.get(y).add('.');
                            }

                            y+=1;
                        }

                        if(richting == "O") {

                            if(x >= maxVerdiepingen.get(y).size()) {
                                maxVerdiepingen.get(y).add('/');

                                for(int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if(y != a) {
                                        maxVerdiepingen.get(a).add('.');
                                    }
                                }

                            } else {
                                maxVerdiepingen.get(y).set(x, '/');
                            }

                            x+=1;
                            y-=1;

                        }
                        if (richting == "N") {

                            if(x < 0) {
                                maxVerdiepingen.get(y).set(x+1, '#');

                            } else if (x > maxVerdiepingen.get(y).size()) {
                                maxVerdiepingen.get(y).set(x-1, '#');
                            } else {
                                maxVerdiepingen.get(y).set(x, '#');
                            }

                            z+=1;
                            y-=1;
                        }

                        if (richting == "W") {

                            if (z <= 0 || maxVerdiepingen.get(y).get(x) == '.') {
                                maxVerdiepingen.get(y).set(x, '\\');

                            } else if(x < 0) {
                                maxVerdiepingen.get(y).add(0,'\\');

                                for(int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if(y != a) {
                                    maxVerdiepingen.get(a).add(0, '.');
                                    }
                                }
                            }

                            x-=1;
                            y-=1;

                        }
                        if(richting == "Z") {
                            if(x <= 0) {
                                maxVerdiepingen.get(y).set(x+1, '#');

                            } else if (x >= maxVerdiepingen.get(y).size()) {
                                maxVerdiepingen.get(y).set(x-1, '#');
                            } else  {
                                maxVerdiepingen.get(y).set(x, '#');
                            }

                            z-=1;
                            y-=1;
                        }

                        break;
                    }
                    case 'D': {
                        System.out.println("D: "+ x);

                        if(maxVerdiepingen.size()-1 < y + 1) {
                            maxVerdiepingen.add(segment);
                            y+=1;

                            for(int a = 0; a < maxVerdiepingen.get(y-1).size(); a++) {
                                maxVerdiepingen.get(y).add('.');
                            }

                        } else if (maxVerdiepingen.get(y).size()-1 >= y + 1 ) {
                            y+=1;
                        }

                        if(richting == "O") {
                            if(x <= 0) {
                                maxVerdiepingen.get(y).add(0, '\\');
                                for (int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if (y != a) {
                                        maxVerdiepingen.get(a).add(0, '.');
                                    }
                                }
                            } else if (x >= maxVerdiepingen.get(y).size()) {
                                maxVerdiepingen.get(y).add('\\');
                                for (int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if (y != a) {
                                        maxVerdiepingen.get(a).add('.');
                                    }
                                }
                            } else if (x > 0 && x < maxVerdiepingen.get(y).size()) {
                                maxVerdiepingen.get(y).set(x, '\\');
                            }

                           x+=1;

                        }
                        if (richting == "N") {

                            if(x <= 0) {
                                maxVerdiepingen.get(y).set(x+1, '8');

                            } else if (x >= maxVerdiepingen.get(y).size()) {
                                maxVerdiepingen.get(y).set(x-1, '8');
                            } else  {
                                maxVerdiepingen.get(y).set(x, '8');
                            }

                            z-=1;

                        }
                        if (richting == "W") {

                            if(x <= 0) {
                                maxVerdiepingen.get(y).add(0, '/');

                                for(int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if(y != a) {
                                        maxVerdiepingen.get(a).add(0, '.');
                                    }
                                }

                            } else if(maxVerdiepingen.get(y).get(x) == '.') {
                                maxVerdiepingen.get(y).set(x, '/');
                            } else if(z <= 0 && maxVerdiepingen.get(y).get(x) == '.')  {
                                maxVerdiepingen.get(y).set(x, '/');
                            }

                            x-=1;

                        };

                        if(richting == "Z") {


                            if(x <= 0) {
                                maxVerdiepingen.get(y).set(x+1, '#');

                            } else if (x >= maxVerdiepingen.get(y).size()) {
                                maxVerdiepingen.get(y).set(x-1, '#');
                            } else  {
                                maxVerdiepingen.get(y).set(x, '#');
                            }

                            z+=1;
                        }

                        break;
                    }
                    case 'L': {
                        System.out.println("L: "+ x);

                        if(richting == "O") {
                            richting = "N";

                            if(x >= maxVerdiepingen.get(y).size()) {
                                maxVerdiepingen.get(y).add('_');
                                for(int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if(y != a) {
                                        maxVerdiepingen.get(a).add('.');
                                    }
                                }
                            } else if(x <= 0) {
                                maxVerdiepingen.get(y).add(0,'_');

                                for(int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if(y != a) {
                                        maxVerdiepingen.get(a).add('.');
                                    }
                                }
                            } else if (x > 0 && x < maxVerdiepingen.get(y).size()) {
                                maxVerdiepingen.get(y).set(x, '_');
                            }

                            z+=1;

                            break;
                        }


                        if (richting == "N") {
                            richting = "W";

                            if(x > maxVerdiepingen.get(y).size()) {
                                maxVerdiepingen.get(y).add('_');
                                for(int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if(y != a) {
                                        maxVerdiepingen.get(a).add('.');
                                    }
                                }
                            } else if(x < 0) {
                                maxVerdiepingen.get(y).add(0,'_');

                                for(int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if(y != a) {
                                        maxVerdiepingen.get(a).add('.');
                                    }
                                }
                            } else if (x >= 0 && x <= maxVerdiepingen.get(y).size()) {
                                maxVerdiepingen.get(y).set(x, '_');
                            }
                            System.out.println(z + " 5645464654654");

                            x-=1;

                            System.out.println(z + " 5645464654654");
                            break;
                        }


                        if (richting == "W") {
                            richting = "Z";

                            if(x < 0) {
                                maxVerdiepingen.get(y).add(0, '_');

                                for(int a = 0; a <= maxVerdiepingen.size()-1; a++) {
                                    if(y != a) {
                                        maxVerdiepingen.get(a).add(0,'.');
                                    }
                                }
                                x=0;
                            } else if (x > maxVerdiepingen.get(y).size()) {
                                maxVerdiepingen.get(y).add( '_');

                                for(int a = 0; a <= maxVerdiepingen.size(); a++) {
                                    if(y != a) {
                                        maxVerdiepingen.get(a).add('.');
                                    }
                                }
                                x = maxVerdiepingen.get(y).size() - 1;
                            } else if (x >= 0 && x <= maxVerdiepingen.get(y).size()) {
                                maxVerdiepingen.get(y).set(x, '_');
                            }

                            z-=1;

                            break;
                        };

                        if(richting == "Z") {
                            System.out.println("55");
                            richting = "O";

                            if(x > maxVerdiepingen.get(y).size()) {
                                maxVerdiepingen.get(y).add('_');

                                for(int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if(y != a) {
                                        maxVerdiepingen.get(a).add('.');
                                    }
                                }
                            } else if(x < 0) {
                                maxVerdiepingen.get(y).add(0,'_');

                                for(int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if(y != a) {
                                        maxVerdiepingen.get(a).add('.');
                                    }
                                }
                            } else if (x > 0 && x < maxVerdiepingen.get(y).size()) {
                                maxVerdiepingen.get(y).set(x, '_');
                            }
                            x+=1;
                        }
                        break;
                    }
                    case 'R': {
                        System.out.println("R: "+ x);

                        if(richting == "O") {

                            richting = "Z";

                            if(x > maxVerdiepingen.get(y).size()) {
                                maxVerdiepingen.get(y).set(x-1, '_');

                            } else if(x < 0) {
                                maxVerdiepingen.get(y).set(x+1, '_');
                            } else  {
                                maxVerdiepingen.get(y).set(x, '_');
                            }

                            z-=1;

                            break;
                        }

                        if (richting == "Z") {
                            richting = "W";

                            if(x >= maxVerdiepingen.get(y).size()) {
                                maxVerdiepingen.get(y).add('_');

                                for(int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if(y != a) {
                                        maxVerdiepingen.get(a).add('.');
                                    }
                                }
                            } else if(x <= 0) {
                                maxVerdiepingen.get(y).add(0,'_');

                                for(int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if(y != a) {
                                        maxVerdiepingen.get(a).add('.');
                                    }
                                }
                            } else if (x > 0 && x < maxVerdiepingen.get(y).size()) {
                                maxVerdiepingen.get(y).set(x, '_');
                            }

                            x-=1;
                            break;
                        }

                        if (richting == "W") {

                            richting = "N";
                            if(x >= maxVerdiepingen.get(y).size()) {
                                maxVerdiepingen.get(y).set(x-1, '_');

                            } else if(x <= 0) {
                                maxVerdiepingen.get(y).set(x+1, '_');
                            } else  {
                                maxVerdiepingen.get(y).set(x, '_');
                            }

                            z+=1;
                            break;
                        };

                        if(richting == "N") {
                            richting = "O";

                            if(x >= maxVerdiepingen.get(y).size()) {
                                maxVerdiepingen.get(y).add('_');

                                for(int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if(y != a) {
                                        maxVerdiepingen.get(a).add('.');
                                    }
                                }
                            } else if(x <= 0) {
                                maxVerdiepingen.get(y).add(0,'_');

                                for(int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if(y != a) {
                                        maxVerdiepingen.get(a).add('.');
                                    }
                                }
                            } else if (x > 0 && x < maxVerdiepingen.get(y).size()) {
                                maxVerdiepingen.get(y).set(x, '_');
                            }

                            x+=1;
                            break;
                        }
                        break;
                    }
                    default: {
                        break;
                    }
                }

            }

            for(int verdieping = 0; verdieping < maxVerdiepingen.size(); verdieping++) {
                System.out.println((i + 1) + " " +maxVerdiepingen.get(verdieping));
            }
        }
    }
}