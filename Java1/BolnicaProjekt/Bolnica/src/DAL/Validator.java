package DAL;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private static final String emailRegex = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private static final String[] poljeKrvnaGrupa = {"A+", "B+", "AB+", "A-", "B-", "AB-", "0+", "0-"};
    private static final char spolMuski = 'M';
    private static final char spolZenski = 'Z';
    public static char[] znakoviMobitela = {'/', '+', '-'};

    public static boolean isTextSlova(String unos) {
        if (isEmpty(unos)) {
            return false;
        }

        char[] poljeZnakova = unos.toCharArray();
        for (char c : poljeZnakova) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isTextMobitel(String unos) {
        if (isEmpty(unos)) {
            return false;
        }
        char[] poljeZnakova = unos.toCharArray();
        for (char c : poljeZnakova) {
            if (Character.isDigit(c) || new String(poljeZnakova).indexOf(c) == -1) {
                return false;
            }
        }
        return true;
    }

    public static boolean isTextKucniBroj(String unos) {
        if (isEmpty(unos)) {
            return false;
        }
        if (!Character.isDigit(unos.charAt(0))) {
            return false;
        }
        if (unos.length() > 4) {
            return false;
        }
        return true;
    }

    public static boolean isTextNumbers(String unos) {
        char[] polje = unos.toCharArray();

        for (char c : polje) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isTextOIB(String oib) {
        if (oib.length() != 11) {
            return false;
        }

        try {
            Long.parseLong(oib);
        } catch (NumberFormatException e) {
            return false;
        }

        int znamenka, zbroj, medjuOstatak, ostatak, umnozak;
        ostatak = 10;

        for (int charIndex = 0; charIndex < oib.length() - 1; charIndex++) {
            znamenka = Integer.parseInt(Character.toString(oib.charAt(charIndex)));
            zbroj = znamenka + ostatak;
            medjuOstatak = zbroj % 10;
            if (medjuOstatak == 0) {
                medjuOstatak = 10;
            }
            umnozak = medjuOstatak * 2;
            ostatak = umnozak % 11;
        }
        int kontrolniBroj = 11 - ostatak;
        if (kontrolniBroj == 10) {
            kontrolniBroj = 0;
        }
        return (Integer.parseInt(Character.toString(oib.charAt(oib.length() - 1))) == kontrolniBroj);
    }

    public static boolean isTextEmail(String email) {
        Pattern pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isTextBloodType(String krvnaGrupa) {
        for (int i = 0; i < poljeKrvnaGrupa.length; i++) {
            if (poljeKrvnaGrupa[i] == krvnaGrupa) {
                return true;
            }
        }
        return false;
    }

    public static boolean isCharSex(char input) {
        char safeInput = Character.toUpperCase(input);
        return (safeInput == spolMuski || safeInput == spolZenski);
    }

    private static boolean isEmpty(String unos) {
        unos.trim();
        if (unos.length() == 0) {
            return true;
        }
        return false;
    }
}
