package DAL;

public class Adresa extends DbType {

    private String ulica;
    private String kucniBroj;
    private int gradId;
    
    public Adresa(String ulica,String kucniBroj,int gradId){
        this.ulica = ulica;
        this.kucniBroj = kucniBroj;
        this.gradId = gradId;
    }

    public String getUlica() {
        return ulica;
    }

    public String getKucniBroj() {
        return kucniBroj;
    }

    public int getGradId() {
        return gradId;
    }
}
