package DAL;

import java.math.BigDecimal;
import java.util.Currency;

public class Profesija extends DbType {

    private String naziv;
    private BigDecimal godisnjiPrihod;

    public Profesija(String naziv, BigDecimal godisnjiPrihod) {
        this.naziv = naziv;
        this.godisnjiPrihod = godisnjiPrihod;
    }

    public String getNaziv() {
        return naziv;
    }

    public BigDecimal getGodisnjiPrihod() {
        return godisnjiPrihod;
    }
}
