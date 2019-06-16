package PL;

import java.util.Random;

public class StringGenerator {

    private static final String malaSlova = "abcdefghijklmnopqrstuvwxyz";
    private static final String velkaSlova = malaSlova.toUpperCase();
    private static final String brojevi = "012345678910";
    private static final String poolBrojeva = malaSlova + velkaSlova + brojevi;
    private static final int duljinaPoola = poolBrojeva.length();
    private static final int duljinaStringa = 14;

    public static String generirajBrojRacuna() {
        Random rnd = new Random(); 
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<duljinaStringa;i++) {
            sb.append(poolBrojeva.charAt(rnd.nextInt(duljinaPoola)));
        }
        return sb.toString();
    }
}
