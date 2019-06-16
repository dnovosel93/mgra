package DAL;

import java.util.Date;

public class MRF extends DbType{
    private int osobaId;
    private String spol;
    private Date datumRodjenja;
    private String opciZapis;
    private String kontakt1;
    private String kontakt2;
    private int rodbinaId;
    private Osoba osoba;
    private Osoba rodbina;
    
    public MRF(Osoba osoba,String spol,Date datumRodjenja,String opciZapis,String kontakt1,String kontakt2,Osoba rodbina){
        this.osoba = osoba;
        this.spol = spol;
        this.datumRodjenja = datumRodjenja;
        this.opciZapis = opciZapis;
        this.kontakt1 = kontakt1;
        this.kontakt2 = kontakt2;
        this.rodbina = rodbina;
    }
    
    public int getOsobaId() {
        return osobaId;
    }

    public String getSpol() {
        return spol;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public String getOpciZapis() {
        return opciZapis;
    }

    public String getKontakt1() {
        return kontakt1;
    }

    public String getKontakt2() {
        return kontakt2;
    }

    public int getRodbinaId() {
        return rodbinaId;
    }
}
