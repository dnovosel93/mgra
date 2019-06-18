package DAL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class DataBase {

    private static DataSource ds = SourceHelper.kreirajDataSource();

    public int createOsoba(Osoba osoba) throws SQLException {
        int idOsoba = -1;

        try (Connection con = ds.getConnection(); CallableStatement stmn = con.prepareCall(ProcedureHelper.procCreateOsoba)) {
            stmn.setString("Ime", osoba.getIme());
            stmn.setString("Prezime", osoba.getPrezime());
            stmn.setString("OIB", osoba.getOIB());
            stmn.registerOutParameter("Id", java.sql.Types.INTEGER);

            stmn.executeUpdate();
            idOsoba = stmn.getInt(4);
        }
        return idOsoba;
    }

    public int createAdresa(Adresa adresa) throws SQLException {
        int idAdresa = -1;

        try (Connection con = ds.getConnection(); CallableStatement stmn = con.prepareCall(ProcedureHelper.procCreateAdresa)) {
            stmn.setString(1, adresa.getUlica());
            stmn.setString(2, adresa.getKucniBroj());
            stmn.setInt(3, adresa.getGradId());
            stmn.registerOutParameter(4, java.sql.Types.INTEGER);

            stmn.executeUpdate();
            idAdresa = stmn.getInt(4);
        }
        return idAdresa;
    }

    public int createUredaj(Uredaj uredaj) throws SQLException {
        int idUredaj = -1;

        try (Connection con = ds.getConnection(); CallableStatement stmn = con.prepareCall(ProcedureHelper.procCreateUredaj)) {
            stmn.setString(1, uredaj.getTelefonPosao());
            stmn.setString(2, uredaj.getTelefonKucni());
            stmn.setString(3, uredaj.getMobitel());
            stmn.setString(4, uredaj.getPager());
            stmn.setString(5, uredaj.getFax());
            stmn.setString(6, uredaj.getEmail());
            stmn.registerOutParameter(7, java.sql.Types.INTEGER);

            stmn.executeUpdate();
            idUredaj = stmn.getInt(7);
        }
        return idUredaj;
    }

    public int createKontakt(Kontakt kontakt) throws SQLException {
        int idKontakt = -1;

        try (Connection con = ds.getConnection(); CallableStatement stmn = con.prepareCall(ProcedureHelper.procCreateKontakt)) {
            stmn.setInt(1, kontakt.getTrenutnaAdresaID());
            stmn.setInt(2, kontakt.getStalnaAdresaID());
            stmn.setInt(3, kontakt.getUredajID());
            stmn.registerOutParameter(4, java.sql.Types.INTEGER);

            stmn.executeUpdate();
            idKontakt = stmn.getInt(4);
        }
        return idKontakt;
    }

    public int createRodbina(Rodbina rodbina) throws SQLException {
        int idRodbina = -1;

        try (Connection con = ds.getConnection(); CallableStatement stmn = con.prepareCall(ProcedureHelper.procCreateRodbina)) {
            stmn.setInt(1, rodbina.getOsobaId());
            stmn.setString(2, rodbina.getOdnosSaPacijentom());
            stmn.setInt(3, rodbina.getKontaktId());
            stmn.registerOutParameter(5, java.sql.Types.INTEGER);

            stmn.executeUpdate();
            idRodbina = stmn.getInt(5);
        }
        return idRodbina;
    }

    public int createOsobniDetalji(OsobniDetalj osobniDetalj) throws SQLException {
        int idOsobniDetalji = -1;

        try (Connection con = ds.getConnection(); CallableStatement stmn = con.prepareCall(ProcedureHelper.procCreateOsobniDetalji)) {
            stmn.setBoolean(1, osobniDetalj.getBracniStatus());
            stmn.setInt(2, osobniDetalj.getBrojUzdrzavanihOsoba());
            stmn.setInt(3, osobniDetalj.getVisina());
            stmn.setInt(4, osobniDetalj.getKilaza());
            stmn.setString(5, osobniDetalj.getKrvnaGrupa());
            stmn.registerOutParameter(6, java.sql.Types.INTEGER);

            stmn.executeUpdate();
            idOsobniDetalji = stmn.getInt(6);
        }
        return idOsobniDetalji;
    }

    public int createProfesija(Profesija profesija) throws SQLException {
        int idProfesija = -1;

        try (Connection con = ds.getConnection(); CallableStatement stmn = con.prepareCall(ProcedureHelper.procCreateProfesija)) {
            stmn.setString(1, profesija.getNaziv());
            stmn.setBigDecimal(2, profesija.getGodisnjiPrihod());
            stmn.registerOutParameter(3, java.sql.Types.INTEGER);

            stmn.executeUpdate();
            idProfesija = stmn.getInt(3);
        }
        return idProfesija;
    }

    public int createLifestyle(Lifestyle lifestyle) throws SQLException {
        int idLifestyle = -1;

        try (Connection con = ds.getConnection(); CallableStatement stmn = con.prepareCall(ProcedureHelper.procCreateLifestyle)) {
            stmn.setBoolean(1, lifestyle.getVegetarijanac());
            stmn.setBoolean(2, lifestyle.getPusac());
            stmn.setInt(3, lifestyle.getAvgBrojCigareta());
            stmn.setBoolean(4, lifestyle.getAlkoholnaPica());
            stmn.setInt(5, lifestyle.getAvgBrojAlkoholnihPica());
            stmn.setInt(6, lifestyle.getAvgBrojKava());
            stmn.setInt(7, lifestyle.getAvgBrojSlatkihPica());
            stmn.setBoolean(8, lifestyle.getRedovnoObroci());
            stmn.setBoolean(9, lifestyle.getKucnaKuhinja());
            stmn.registerOutParameter(10, java.sql.Types.INTEGER);

            stmn.executeUpdate();
            idLifestyle = stmn.getInt(10);
        }
        return idLifestyle;
    }

    public int createMRF(MRF mrf) throws SQLException {
        int idMRF = -1;

        try (Connection con = ds.getConnection(); CallableStatement stmn = con.prepareCall(ProcedureHelper.procCreateMRF)) {
            stmn.setInt(1, mrf.getOsobaId());
            stmn.setString(2, mrf.getSpol());
        }
        return idMRF;
    }

    public List<Drzava> gatDrzave() throws SQLException {
        List<Drzava> listaDrzava = new ArrayList<>();
        
        try(Connection con = ds.getConnection();CallableStatement stm = con.prepareCall(ProcedureHelper.viewReadDrzave)){
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                Drzava d = new Drzava();
                d.setId(rs.getInt("Id"));
                d.setNaziv(rs.getString("Naziv"));
                listaDrzava.add(d);
            }
        }
        return listaDrzava;
    }

    public List<Grad> getGradoviDrzave(int drzavaId) throws SQLException {
        List<Grad> listaGradova = new ArrayList<>();

        try (Connection con = ds.getConnection(); CallableStatement stmn = con.prepareCall(ProcedureHelper.procReadGradoviDrzave)) {
            stmn.setInt("@DrzavaId", drzavaId);

            ResultSet rs = stmn.executeQuery();

            while (rs.next()) {
                Grad g = new Grad();
                g.setId(rs.getInt("Id"));
                g.setNaziv(rs.getString("Naziv"));
                g.setPostanskiBroj(rs.getString("PostanskiBroj"));
                listaGradova.add(g);
            }
        }
        return listaGradova;
    }
}
