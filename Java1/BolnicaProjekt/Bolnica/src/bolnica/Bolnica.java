package bolnica;

import DAL.DataBase;
import DAL.Osoba;
import PL.Inputs;
import PL.Menu;
import PL.StringGenerator;
import java.sql.SQLException;

public class Bolnica {
    //Random OIB
    //30887715948
    //57883525194
    //29085257141
    
    public static void main(String[] args) {
       Osoba o = new Osoba();
       
      Menu menu = new Menu();
       menu.registracijaKorisnika();
    }

}
