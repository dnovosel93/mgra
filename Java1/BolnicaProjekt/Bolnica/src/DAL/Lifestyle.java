package DAL;

public class Lifestyle extends DbType{

    private boolean vegetarijanac;
    private boolean pusac;
    private int avgBrojCigareta;
    private boolean alkoholnaPica;
    private int avgBrojAlkoholnihPica;
    private int avgBrojKava;
    private int avgBrojSlatkihPica;
    private boolean redovnoObroci;
    private boolean kucnaKuhinja;

    public Lifestyle(boolean vegetarijanac, boolean pusac, int avgBrojCigareta,
            boolean alkoholnaPica, int avgBrojAlkoholnihPica, int avgBrojKava,
            int avgBrojSlatkihPica, boolean redovnoObroci, boolean kucnaKuhinja) {
        this.vegetarijanac = vegetarijanac;
        this.pusac = pusac;
        this.avgBrojCigareta = avgBrojCigareta;
        this.alkoholnaPica = alkoholnaPica;
        this.avgBrojAlkoholnihPica = avgBrojAlkoholnihPica;
        this.avgBrojKava = avgBrojKava;
        this.avgBrojSlatkihPica = this.avgBrojSlatkihPica;
        this.redovnoObroci = redovnoObroci;
        this.kucnaKuhinja = kucnaKuhinja;
    }

    public boolean getVegetarijanac() {
        return vegetarijanac;
    }

    public boolean getPusac() {
        return pusac;
    }

    public int getAvgBrojCigareta() {
        return avgBrojCigareta;
    }

    public boolean getAlkoholnaPica() {
        return alkoholnaPica;
    }

    public int getAvgBrojAlkoholnihPica() {
        return avgBrojAlkoholnihPica;
    }

    public int getAvgBrojKava() {
        return avgBrojKava;
    }

    public int getAvgBrojSlatkihPica() {
        return avgBrojSlatkihPica;
    }

    public boolean getRedovnoObroci() {
        return redovnoObroci;
    }

    public boolean getKucnaKuhinja() {
        return kucnaKuhinja;
    }
}
