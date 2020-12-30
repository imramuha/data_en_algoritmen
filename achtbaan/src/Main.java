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
        System.out.println(aantalAchtbanen);

        for(int i = 0; i < aantalAchtbanen; i++) {

            String richting = "O";

            // position in de kolom
            int x = 0;

            int y = 0;
            // gaat dninge hebben op overriting
            int z = 0;

            // aantal nveaus/stappen kan nooit hoger zijn dan aantal stappen

            int aantalStappen = scanner.nextInt();
            //System.out.println(aantalStappen);

            ArrayList<ArrayList<Character>> maxVerdiepingen = new ArrayList<ArrayList<Character>>(aantalStappen);
            ArrayList<Character> maxBewegingen = new ArrayList<Character>(aantalStappen);
            maxVerdiepingen.add(maxBewegingen);

            String stappen = scanner.next();

            for(int j = 0; j < aantalStappen;j++) {
                ArrayList<Character> segment = new ArrayList<Character>(aantalStappen);

                System.out.println(maxVerdiepingen);

                switch(stappen.charAt(j)) {
                    case 'S': {
                        maxVerdiepingen.get(y).add('=');
                        x+=1;

                        break;
                    }
                    case 'V': {

                        if(richting == "O") {
                            if(x > maxVerdiepingen.get(y).size() - 1 ) {
                                maxVerdiepingen.get(y).add('o');

                                for(int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if(y != a) {
                                        maxVerdiepingen.get(a).add('.');
                                    }
                                }

                            } else if(x < 0) {

                                maxVerdiepingen.get(y).add(0, '0');

                                for(int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if(y != a) {
                                        maxVerdiepingen.get(a).add(0,'.');
                                    }
                                }
                            } else if (x < maxVerdiepingen.get(y).size() && x >= 0) {

                                if (z <= 0) {
                                    maxVerdiepingen.get(y).set(x,'O');
                                }
                            }

                            x+=1;
                        }

                        if (richting == "N") {
                            z+=1;
                        }

                        if (richting == "W") {

                            if(x < 0 ) {
                                maxVerdiepingen.get(y).add(0,'w');

                                for(int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if(y != a) {
                                        maxVerdiepingen.get(a).add(0,'.');
                                    }
                                }


                            } else {
                                System.out.println("thing");
                                System.out.println(x);

                                if(maxVerdiepingen.get(y).get(x) == '.' ||  z < 0) {
                                    maxVerdiepingen.get(y).set(x,'W');
                                }
                            }

                            x-=1;

                        }
                        if(richting == "Z") {
                            z-=1;
                        }

                        break;
                    }
                    case 'U': {

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
                                maxVerdiepingen.get(y).add(0, '#');
                                for (int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if (y != a) {
                                        maxVerdiepingen.get(a).add(0, '.');
                                    }
                                }
                            } else if (x > maxVerdiepingen.get(y).size()-1) {
                                maxVerdiepingen.get(y).add('#');
                                for (int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if (y != a) {
                                        maxVerdiepingen.get(a).add('.');
                                    }
                                }
                            } else if (x >= 0 && x <= maxVerdiepingen.get(y).size() -1){
                                maxVerdiepingen.get(y).set(x,'#');
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
                                x+=1;
                            }

                            x-=1;
                            y-=1;

                        }
                        if(richting == "Z") {

                            if(x<0)
                                maxVerdiepingen.get(y).add(0,'#');
                                for(int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if(y != a) {
                                        maxVerdiepingen.get(a).add(0, '.');
                                    }
                                }
                            if(x>maxVerdiepingen.get(y).size()-1) {
                                maxVerdiepingen.get(y).add('#');
                                for (int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if (y != a) {
                                        maxVerdiepingen.get(a).add('.');
                                    }
                                }
                            }
                            else
                                maxVerdiepingen.get(y).set(x,'#');


                            z-=1;
                            y-=1;
                        }

                        break;
                    }
                    case 'D': {

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
                            if(x > maxVerdiepingen.get(y).size() - 1 ) {
                                maxVerdiepingen.get(y).add('\\');

                                for(int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if(y != a) {
                                        maxVerdiepingen.get(a).add('.');
                                    }
                                }

                            }
                           if(z <= 0 || maxVerdiepingen.get(y).get(x) == '.') {
                                maxVerdiepingen.get(y).set(x, '\\');
                           }

                            x+=1;

                        }
                        if (richting == "N") {

                            if(x < 0) {
                                maxVerdiepingen.get(y).add(0, '#');
                                for (int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if (y != a) {
                                        maxVerdiepingen.get(a).add(0, '.');
                                    }
                                }
                            } else if (x>maxVerdiepingen.get(y).size()-1) {
                                maxVerdiepingen.get(y).add('#');
                                for (int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if (y != a) {
                                        maxVerdiepingen.get(a).add('.');
                                    }
                                }
                            } else if (x >= 0 && x <= maxVerdiepingen.get(y).size() - 1) {
                                maxVerdiepingen.get(y).set(x, '#');
                            }

                            z+=1;


                        }
                        if (richting == "W") {

                            if(x < 0) {
                                maxVerdiepingen.get(y).add(0, '/');

                                for(int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if(y != a) {
                                        maxVerdiepingen.get(a).add(0, '.');
                                    }
                                }
                                x+=1;

                            } else if(maxVerdiepingen.get(y).get(x) == '.') {
                                maxVerdiepingen.get(y).set(x, '/');
                            } else if(z <= 0 && maxVerdiepingen.get(y).get(x) == '.')  {
                                maxVerdiepingen.get(y).set(x, '/');
                            }

                            x-=1;

                        };

                        if(richting == "Z") {

                            if(x < 0) {
                                maxVerdiepingen.get(y).set(0,'#');

                                for(int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if(y != a) {
                                        maxVerdiepingen.get(a).add(0, '.');
                                    }
                                }
                            } else if(x > maxVerdiepingen.get(y).size()-1) {
                                maxVerdiepingen.get(y).add('#');
                                for (int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if (y != a) {
                                        maxVerdiepingen.get(a).add('.');
                                    }
                                }
                            } else if (x >= 0 && x <= maxVerdiepingen.get(y).size() - 1) {
                                maxVerdiepingen.get(y).set(x, '#');
                            }

                        }

                        z-=1;

                        break;
                    }
                    case 'L': {

                        if(richting == "O") {
                            richting = "N";

                            if(x > maxVerdiepingen.get(y).size()-1) {
                                maxVerdiepingen.get(y).add('6');
                                for(int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if(y != a) {
                                        maxVerdiepingen.get(a).add('.');
                                    }
                                }

                            } else if (x < 0) {
                                maxVerdiepingen.get(y).add(0,'6');
                                for(int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if(y != a) {
                                        maxVerdiepingen.get(a).add(0,'.');
                                    }
                                }
                            } else {
                                maxVerdiepingen.get(y).set(x, '6');
                            }

                            z+=1;

                            break;
                        }
                        if (richting == "N") {
                            richting = "W";

                            if(x > maxVerdiepingen.get(y).size()-1) {
                                maxVerdiepingen.get(y).add('N');
                                for(int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if(y != a) {
                                        maxVerdiepingen.get(a).add('.');
                                    }
                                }
                            } else if(x < 0) {
                                maxVerdiepingen.get(y).add(0,'N');

                                for(int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if(y != a) {
                                        maxVerdiepingen.get(a).add('.');
                                    }
                                }
                            } else if (x >= 0 && x <= maxVerdiepingen.get(y).size() -1) {
                                maxVerdiepingen.get(y).set(x, 'n');
                            }
                            x-=1;
                            break;
                        }
                        if (richting == "W") {
                            richting = "Z";

                            if(x < 0) {
                                maxVerdiepingen.get(y).add(0, '7');

                                for(int a = 0; a <= maxVerdiepingen.size()-1; a++) {
                                    if(y != a) {
                                        maxVerdiepingen.get(a).add(0,'.');
                                    }
                                }
                                x=0;
                            } else if (x > maxVerdiepingen.get(y).size() - 1) {
                                maxVerdiepingen.get(y).add( '7');

                                for(int a = 0; a <= maxVerdiepingen.size()-1; a++) {
                                    if(y != a) {
                                        maxVerdiepingen.get(a).add('.');
                                    }
                                }
                                x = maxVerdiepingen.get(y).size() - 1;
                            } else if (x >= 0 && x <= maxVerdiepingen.get(y).size() - 1) {
                                maxVerdiepingen.get(y).set(x, '7');
                            }

                            z-=1;

                            break;
                        };

                        if(richting == "Z") {
                            richting = "O";

                            System.out.println(x + " :5454");

                            if(x < 0) {
                                maxVerdiepingen.get(y).add(0, 'L');

                                for(int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if(y != a) {
                                        maxVerdiepingen.get(a).add(0, '.');
                                    }
                                }
                            } else if (x >= 0 && x <= maxVerdiepingen.get(y).size() -1 ) {
                                maxVerdiepingen.get(y).set(x, 'L');
                            }

                            x+=1;

                        }
                        break;
                    }
                    case 'R': {

                        if(richting == "O") {
                            richting = "Z";
                            z-=1;
                            x-=1;
                            break;
                        }

                        if (richting == "Z") {
                            richting = "W";

                            if(x > 0) {
                                maxVerdiepingen.get(y).add('R');

                                for(int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if(y != a) {
                                        maxVerdiepingen.get(a).add( '.');
                                    }
                                }
                            }

                            x-=1;

                            break;
                        }
                        if (richting == "W") {
                            richting = "N";
                            z+=1;
                            //x+=1;
                            break;
                        };
                        if(richting == "N") {
                            richting = "O";

                            if(x <= 0) {
                                maxVerdiepingen.get(y).add(0, 'R');

                                for(int a = 0; a < maxVerdiepingen.size(); a++) {
                                    if(y != a) {
                                        maxVerdiepingen.get(a).add(0, '.');
                                    }
                                }
                            } else if (x > 0) {
                                maxVerdiepingen.get(y).set(x, 'R');
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

            System.out.println(x);
            System.out.println(y);
            System.out.println(z);

            for(int p = 0; p < maxVerdiepingen.size(); p++) {
                System.out.println(maxVerdiepingen.get(p));
            }
        }
    }
}