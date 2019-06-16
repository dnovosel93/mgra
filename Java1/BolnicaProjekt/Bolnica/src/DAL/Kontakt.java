package DAL;

public class Kontakt extends DbType {

    private int trenutnaAdresaID;
    private int stalnaAdresaID;
    private int uredajID;
    private Adresa trenutnaAdresa;
    private Adresa stalnaAdresa;
    private Uredaj uredaj;

    public Kontakt(Adresa trenutnaAdresa, Adresa stalnaAdresa, Uredaj uredaj) {
        this.trenutnaAdresa = trenutnaAdresa;
        this.stalnaAdresa = stalnaAdresa;
        this.uredaj = uredaj;
    }

    public int getTrenutnaAdresaID() {
        return trenutnaAdresaID;
    }

    public int getStalnaAdresaID() {
        return stalnaAdresaID;
    }

    public int getUredajID() {
        return uredajID;
    }
}
