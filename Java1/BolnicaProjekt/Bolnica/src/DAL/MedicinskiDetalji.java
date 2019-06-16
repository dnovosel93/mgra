package DAL;

public class MedicinskiDetalji extends DbType {

    private boolean dijabeticar;
    private boolean hipertenzivan;
    private String srcanoStanje;
    private String respiratornoStanje;
    private String probavnoStanje;
    private String ortopedskoStanje;
    private String misicnoStanje;
    private String neuroloskoStanje;
    private String alergije;
    private String lijekoviNuspojave;
    private String prijasnjeOperacije;

    public MedicinskiDetalji(boolean dijabeticar, boolean hipertenzivan,
            String srcanoStanje, String respiratornoStanje, String probavnoStanje,
            String ortopedskoStanje, String misicnoStanje, String neuroloskoStanje,
            String alergije, String lijekoviNuspojave, String prijasnjeOperacije) {
        this.dijabeticar = dijabeticar;
        this.hipertenzivan = hipertenzivan;
        this.srcanoStanje = srcanoStanje;
        this.respiratornoStanje = respiratornoStanje;
        this.probavnoStanje = probavnoStanje;
        this.ortopedskoStanje = ortopedskoStanje;
        this.misicnoStanje = misicnoStanje;
        this.neuroloskoStanje = neuroloskoStanje;
        this.alergije = alergije;
        this.lijekoviNuspojave = lijekoviNuspojave;
        this.prijasnjeOperacije = prijasnjeOperacije;
    }
}
