package DAL;

public class Osoba extends DbType {

    private String ime;
    private String prezime;
    private String OIB;
    
    public Osoba(){};
    
    public Osoba(String ime,String prezime,String OIB){
        this.ime = ime;
        this.prezime = prezime;
        this.OIB = OIB;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getOIB() {
        return OIB;
    }
}
