package bolnica;

import DAL.DataBase;
import DAL.Drzava;
import DAL.Grad;
import DAL.Osoba;
import DAL.OsobniDetalj;
import DAL.Validator;
import PL.Inputs;
import PL.Menu;
import PL.StringGenerator;
import java.io.Console;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Bolnica {
    //Random OIB
    //30887715948
    //57883525194
    //29085257141

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(Validator.isTextBloodType("A+"));
        OsobniDetalj osobniDetalj = Inputs.unosOsobniDetalj();
    }
}
