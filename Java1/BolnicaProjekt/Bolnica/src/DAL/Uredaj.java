package DAL;

public class Uredaj extends DbType {

    private String telefonPosao;
    private String telefonKucni;
    private String mobitel;
    private String pager;
    private String fax;
    private String email;

    public Uredaj(String telefonPosao, String telefonKucni, String mobitel, String pager, String fax, String email) {
        this.telefonPosao = telefonPosao;
        this.telefonKucni = telefonKucni;
        this.mobitel = mobitel;
        this.pager = pager;
        this.fax = fax;
        this.email = email;
    }

    public String getTelefonPosao() {
        return telefonPosao;
    }

    public String getTelefonKucni() {
        return telefonKucni;
    }

    public String getMobitel() {
        return mobitel;
    }

    public String getPager() {
        return pager;
    }

    public String getFax() {
        return fax;
    }

    public String getEmail() {
        return email;
    }
}
