package DAL;

public class Rodbina extends DbType {

    private int osobaId;
    private String odnosSaPacijentom;
    private int kontaktId;
    private Osoba osoba;
    private Kontakt kontakt;
    
    public Rodbina(Osoba osoba,String odnosSaPacijentom,Kontakt kontakt){
        this.osoba = osoba;
        this.odnosSaPacijentom = odnosSaPacijentom;
        this.kontakt = kontakt;
    }
    
    public int getOsobaId() {
        return osobaId;
    }

    public String getOdnosSaPacijentom() {
        return odnosSaPacijentom;
    }

    public int getKontaktId() {
        return kontaktId;
    }
}
