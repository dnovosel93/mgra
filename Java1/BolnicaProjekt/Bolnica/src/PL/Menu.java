package PL;

import DAL.CRF;
import DAL.DataBase;
import DAL.Kontakt;

public class Menu {

    private DataBase db = new DataBase();
    
    private static String getMenuTxt() {
        StringBuilder sb = new StringBuilder();

        sb.append("MENU");
        sb.append("[1] Registracija korisnika");
        sb.append("[2] Brza registracija korisnika");
        sb.append("[3] Zakazi tretman");
        sb.append("[4] Zakazi labaratorijsku analizu");
        sb.append("[5] Naplati racun");
        sb.append("[0] EXIT");

        return sb.toString();
    }

    public static String menuTxt = getMenuTxt();

    private void start() {
        System.out.println(menuTxt);

        int odabir = -1;

        try {
            odabir = Inputs.inputInteger("ODABIR");
            if (odabir < 1 && odabir > 4) {
                throw new Exception(String.valueOf(odabir));
            }
        } catch (NumberFormatException exc) {
            System.out.println("Pogresan unos\nPokusajte ponovo");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        switch (odabir) {
            case 1:
                registracijaKorisnika();
                break;
            case 2:

        }
    }

    public void registracijaKorisnika() {
        System.out.println("***REGISTRACIJA KORISNIKA***");
        Inputs.unosCRF();
    }
}
