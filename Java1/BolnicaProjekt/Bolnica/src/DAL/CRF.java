package DAL;

import java.util.Date;

public class CRF {
    private int osobaId;
    private Date datumRodjenja;
    private char spol;
    private int kontaktId;
    private int rodbinaId;
    private int osobniDetaljiId;
    private int profesijaId;
    private int lifestyleId;
    private int osnovnaPrituzbaId;
    private int medicinskiDetaljiId;
    private Date datumRegistracije;
    private Osoba osoba;
    private Kontakt kontakt;
    private Rodbina rodbina;
    private OsobniDetalj osobniDetalj;
    private Profesija profesija;
    private Lifestyle lifestyle;
    private OsnovnaPrituzba osnovnaPrituzba;
    private MedicinskiDetalji medicinskiDetalji;
    

    public CRF(Osoba osoba,Date datumRodjenja,char spol,Kontakt kontakt,Rodbina rodbina,OsobniDetalj osobniDetalj,Profesija profesija,Lifestyle lifestyle,OsnovnaPrituzba osnovnaPrituzba,MedicinskiDetalji medicinskiDetalji,Date datumRegistracije){
        this.osoba = osoba;
        this.datumRodjenja = datumRodjenja;
        this.spol = spol;
        this.kontakt = kontakt;
        this.rodbina = rodbina;
        this.osobniDetalj = osobniDetalj;
        this.profesija = profesija;
        this.lifestyle = lifestyle;
        this.osnovnaPrituzba = osnovnaPrituzba;
        this.medicinskiDetalji = medicinskiDetalji;
        this.datumRegistracije = datumRegistracije;
    }

    public int getOsobaId() {
        return osobaId;
    }

    public void setOsobaId(int osobaId) {
        this.osobaId = osobaId;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public char getSpol() {
        return spol;
    }

    public void setSpol(char spol) {
        this.spol = spol;
    }

    public int getKontaktId() {
        return kontaktId;
    }

    public void setKontaktId(int kontaktId) {
        this.kontaktId = kontaktId;
    }

    public int getRodbinaId() {
        return rodbinaId;
    }

    public void setRodbinaId(int rodbinaId) {
        this.rodbinaId = rodbinaId;
    }

    public int getOsobniDetaljiId() {
        return osobniDetaljiId;
    }

    public void setOsobniDetaljiId(int osobniDetaljiId) {
        this.osobniDetaljiId = osobniDetaljiId;
    }

    public int getProfesijaId() {
        return profesijaId;
    }

    public void setProfesijaId(int profesijaId) {
        this.profesijaId = profesijaId;
    }

    public int getLifestyleId() {
        return lifestyleId;
    }

    public void setLifestyleId(int lifestyleId) {
        this.lifestyleId = lifestyleId;
    }

    public int getOsnovnaPrituzbaId() {
        return osnovnaPrituzbaId;
    }

    public void setOsnovnaPrituzbaId(int osnovnaPrituzbaId) {
        this.osnovnaPrituzbaId = osnovnaPrituzbaId;
    }

    public int getMedicinskiDetaljiId() {
        return medicinskiDetaljiId;
    }

    public void setMedicinskiDetaljiId(int medicinskiDetaljiId) {
        this.medicinskiDetaljiId = medicinskiDetaljiId;
    }

    public Date getDatumRegistracije() {
        return datumRegistracije;
    }

    public void setDatumRegistracije(Date datumRegistracije) {
        this.datumRegistracije = datumRegistracije;
    }

    public Osoba getOsoba() {
        return osoba;
    }

    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
    }

    public Kontakt getKontakt() {
        return kontakt;
    }

    public void setKontakt(Kontakt kontakt) {
        this.kontakt = kontakt;
    }

    public Rodbina getRodbina() {
        return rodbina;
    }

    public void setRodbina(Rodbina rodbina) {
        this.rodbina = rodbina;
    }

    public OsobniDetalj getOsobniDetalj() {
        return osobniDetalj;
    }

    public void setOsobniDetalj(OsobniDetalj osobniDetalj) {
        this.osobniDetalj = osobniDetalj;
    }

    public Profesija getProfesija() {
        return profesija;
    }

    public void setProfesija(Profesija profesija) {
        this.profesija = profesija;
    }

    public Lifestyle getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(Lifestyle lifestyle) {
        this.lifestyle = lifestyle;
    }

    public OsnovnaPrituzba getOsnovnaPrituzba() {
        return osnovnaPrituzba;
    }

    public void setOsnovnaPrituzba(OsnovnaPrituzba osnovnaPrituzba) {
        this.osnovnaPrituzba = osnovnaPrituzba;
    }

    public MedicinskiDetalji getMedicinskiDetalji() {
        return medicinskiDetalji;
    }

    public void setMedicinskiDetalji(MedicinskiDetalji medicinskiDetalji) {
        this.medicinskiDetalji = medicinskiDetalji;
    }
}
