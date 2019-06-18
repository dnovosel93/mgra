package DAL;

public class Grad extends DbType{
    private String naziv;
    private String postanskiBroj;
    private int drzavaId;
    
    public Grad(){};
    
    public Grad(int id,String naziv,String postanskiBroj,int drzavaId){
        this.setId(id);
        this.naziv = naziv;
        this.postanskiBroj = postanskiBroj;
        this.drzavaId = drzavaId;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setPostanskiBroj(String postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    public void setDrzavaId(int drzavaId) {
        this.drzavaId = drzavaId;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getPostanskiBroj() {
        return postanskiBroj;
    }

    public int getDrzavaId() {
        return drzavaId;
    }
    
    @Override
    public String toString(){
        return String.format("%d %s %s",this.getId(),this.naziv,this.postanskiBroj);
    }
}
