

public class Main {

    public static void main(String[] args) {

        Try m = new Try();
        m.CalcDistance();
    }
}

class Try {

    public int CalcDistance() {
        int totalX = 0 - 150;
        int totalY = 600 - 750;

        System.out.println((int)Math.ceil(Math.sqrt(Math.pow(totalX,2) + Math.pow(totalY,2))));
        return (int)Math.ceil(Math.sqrt(Math.pow(totalX,2) + Math.pow(totalY,2)));
    }

}