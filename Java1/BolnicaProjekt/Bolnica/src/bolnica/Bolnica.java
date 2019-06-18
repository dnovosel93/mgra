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
        DataBase database = new DataBase();

        try {
            List<Drzava> listaGradova = database.gatDrzave();
            for (Drzava d : listaGradova) {
                System.out.println(d.toString());
            }
        }
        catch(SQLException ex){
            System.out.println(ex);
            System.out.println(ex.getMessage());
            System.out.println(ex.getErrorCode());
        }
    }
}
