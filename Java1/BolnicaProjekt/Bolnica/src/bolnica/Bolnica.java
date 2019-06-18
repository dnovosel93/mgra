package bolnica;

import DAL.DataBase;
import DAL.Drzava;
import DAL.Grad;
import DAL.Osoba;
import PL.Inputs;
import PL.Menu;
import PL.StringGenerator;
import java.sql.SQLException;
import java.util.List;

public class Bolnica {
    //Random OIB
    //30887715948
    //57883525194
    //29085257141

    public static void main(String[] args) {
       Menu menu = new Menu();
       menu.registracijaKorisnika();
    }
}
