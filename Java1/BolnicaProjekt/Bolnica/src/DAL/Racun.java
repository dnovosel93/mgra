package DAL;

import PL.StringGenerator;
import java.math.BigDecimal;

public class Racun {

    private String brojRacuna;
    private BigDecimal ukupnaCijena;
    private int kreditnaKarticaId;
    private int counte = 0;
    
    private Racun(String brojRacuna, BigDecimal ukupnaCijena, int kreditnaKartica) {
        this.brojRacuna = brojRacuna;
        this.ukupnaCijena = ukupnaCijena;
        this.kreditnaKarticaId = kreditnaKartica;
    }

    public Racun(BigDecimal ukupnaCijena, int kreditnaKartica) {
        this(StringGenerator.generirajBrojRacuna(),ukupnaCijena,kreditnaKartica);
    }
}
