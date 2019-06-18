package DAL;

public class Drzava extends DbType{
    private String naziv;

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }
    
    @Override
    public String toString(){
        return String.format("[%d] %s", this.getId(),this.getNaziv());
    }
}
