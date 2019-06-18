package DAL;

public class ProcedureHelper {
    public static final String procCreateOsoba = "{CALL CreateOsoba(?,?,?,?)}";
    public static final String procCreateAdresa = "{CALL CreateAdresa(?,?,?,?,?)}";
    public static final String procCreateUredaj = "{CALL CreateUredaj(?,?,?,?,?,?,?)}";
    public static final String procCreateKontakt = "{CALL CreateKontak(?,?,?,?)}";
    public static final String procCreateRodbina = "{CALL CreateRodbina(?,?,?,?)}";
    public static final String procCreateOsobniDetalji = "{CALL CreateOsobniDetalj(?,?,?,?,?,?)}";
    public static final String procCreateProfesija = "{CALL CreateProfesija(?,?,?)}";
    public static final String procCreateLifestyle = "{CALL CreateLifestyle(?,?,?,?,?,?,?,?,?,?)}";
    public static final String procCreateMRF = "{CALL CreateMRF(?,?,?,?,?,?,?)}";
    
    public static final String viewReadDrzave = "SELECT Id,Naziv FROM ReadDrzave";
    public static final String procReadGradoviDrzave = "{CALL ReadGradoviDrzave(?)}";
    
    //Osoba
    public static final String paramOsobaIme = "Ime";
    public static final String paramOsobaPrezime = "Prezime";
    public static final String paramOsobaOIB = "OIB";
}
