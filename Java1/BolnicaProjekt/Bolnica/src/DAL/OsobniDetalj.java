package DAL;

public class OsobniDetalj extends DbType {

    private boolean bracniStatus;
    private int brojUzdrzavanihOsoba;
    private int visina;
    private int kilaza;
    private String krvnaGrupa;
    
    public OsobniDetalj(boolean bracniStatus,int brojUzdrzavanihOsoba,int visina,int kilaza,String krvnaGrupa){
        this.bracniStatus = bracniStatus;
        this.brojUzdrzavanihOsoba = brojUzdrzavanihOsoba;
        this.visina = visina;
        this.kilaza = kilaza;
        this.krvnaGrupa = krvnaGrupa;
    }

    public boolean getBracniStatus() {
        return bracniStatus;
    }

    public int getBrojUzdrzavanihOsoba() {
        return brojUzdrzavanihOsoba;
    }

    public int getVisina() {
        return visina;
    }

    public int getKilaza() {
        return kilaza;
    }

    public String getKrvnaGrupa() {
        return krvnaGrupa;
    }
}
