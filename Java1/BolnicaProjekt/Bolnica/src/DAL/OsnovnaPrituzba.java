package DAL;

public class OsnovnaPrituzba extends DbType{
    private String izjava;
    private String prijasnjiTretmani;
    private boolean bolnickoLjecenje;
    
    public OsnovnaPrituzba(String izjava,String prijasnjiTretmani,boolean  bolnickoLjecenje){
        this.izjava = izjava;
        this.prijasnjiTretmani = prijasnjiTretmani;
        this.bolnickoLjecenje = bolnickoLjecenje;
    }
}
