package PL;

import DAL.Adresa;
import DAL.CRF;
import DAL.Kontakt;
import DAL.Lifestyle;
import DAL.MedicinskiDetalji;
import DAL.OsnovnaPrituzba;
import DAL.OsobniDetalj;
import DAL.Osoba;
import DAL.Profesija;
import DAL.Rodbina;
import DAL.Uredaj;
import DAL.Validator;
import java.math.BigDecimal;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import jdk.nashorn.internal.ir.ContinueNode;

public class Inputs {

    private static Scanner sc = new Scanner(System.in);
    private static final String msgWordFormat = "rijec";
    private static final String msgWordWithOnlyNumbersFormat = "brojevi";
    private static final String msgOibFormat = "OIB(11 brojeva)";
    private static final String msgPostanskiBrojFormat = "postanski broj (brojevi bez razmaka)";
    private static final String msgKrvnaGrupaFormat = "krvna grupa (A+,A-,B+,B-,AB+,AB-,0+,0-)";
    private static final String msgBrojFormat = "broj";
    private static final String msgBoolFormat = "Da/Ne";
    private static final String msgDatumDanFormat = "Dan (1-31)";
    private static final String msgDatumMjesecFormat = "Mjesec (1-12)";
    private static final String msgDatumGodinaFormat = "Godina (1900-2019)";
    private static final String msgSpolFormat = "(M|Z)";

    public static Osoba unosOsoba() {
        String ime;
        String prezime;
        String oib;

        do {
            ime = inputText("Ime");

            if (!Validator.isWord(ime)) {
                exceptionFormatMsg("IME", msgWordFormat);
            }
        } while (!Validator.isWord(ime));

        do {
            prezime = inputText("Prezime");

            if (!Validator.isWord(prezime)) {
                exceptionFormatMsg("PREZIME", msgWordFormat);
            }
        } while (!Validator.isWord(prezime));

        do {
            oib = inputText("OIB");
            if (!Validator.isOIB(oib)) {
                exceptionFormatMsg("OIB", msgOibFormat);
            }
        } while (!Validator.isOIB(oib));

        Osoba osoba = new Osoba(ime, prezime, oib);
        return osoba;
    }

    public static Adresa unosAdresa() {
        String ulica;
        String grad;
        String drzava;
        String postanskiBroj;

        do {
            ulica = inputText("Ulica");
            if (!Validator.isWord(ulica)) {
                exceptionFormatMsg("ULICA", msgWordFormat);
            }
        } while (!Validator.isWord(ulica));

        do {
            grad = inputText("Grad");
            if (!Validator.isWord(grad)) {
                exceptionFormatMsg("GRAD", msgWordFormat);
            }
        } while (!Validator.isWord(grad));

        do {
            drzava = inputText("Drzava");
            if (!Validator.isWord(drzava)) {
                exceptionFormatMsg("DRZAVA", msgWordFormat);
            }
        } while (!Validator.isWord(drzava));

        do {
            postanskiBroj = inputText("Postanski broj");
            if (!Validator.isWordWithOnlyNumbers(postanskiBroj)) {
                exceptionFormatMsg("POSTANSKI BROJ", grad);
            }
        } while (!Validator.isWordWithOnlyNumbers(postanskiBroj));

        Adresa adresa = new Adresa(ulica, grad, drzava, postanskiBroj);
        return adresa;
    }

    public static Uredaj unosUredaj() {
        String telefonPosao;
        String telefonKucni;
        String mobitel;
        String pager;
        String fax;
        String email;
        boolean validan = true;

        do {
            telefonPosao = inputText("Telefon posao");
        } while (!validan);

        telefonKucni = inputText("Telefon kucni");
        mobitel = inputText("Mobitel");
        pager = inputText("Pager");
        fax = inputText("Fax");
        email = inputText("Email");
        Uredaj uredjaj = new Uredaj(telefonPosao, telefonKucni, mobitel, pager, fax, email);
        return uredjaj;
    }

    public static Kontakt unosKontakt() {
        Adresa trenutnaAdresa;
        Adresa stalnaAdresa;
        Uredaj uredaj;

        trenutnaAdresa = unosAdresa();
        stalnaAdresa = unosAdresa();
        uredaj = unosUredaj();

        Kontakt kontak = new Kontakt(trenutnaAdresa, stalnaAdresa, uredaj);
        return kontak;
    }

    public static Rodbina unosRodbina() {
        Osoba osoba;
        String odnosSaPacijentom;
        Kontakt kontakt;

        osoba = unosOsoba();

        do {
            odnosSaPacijentom = inputText("Odnos sa pacijentom");

            if (!Validator.isWord(odnosSaPacijentom)) {
                exceptionFormatMsg("Odnos sa pacijentom", msgWordFormat);
            }
        } while (!Validator.isWord(odnosSaPacijentom));

        kontakt = unosKontakt();

        Rodbina rodbina = new Rodbina(osoba, odnosSaPacijentom, kontakt);
        return rodbina;
    }

    public static OsobniDetalj unosOsobniDetalj() {
        boolean bracniStatus;
        int brojUzdrzavanihOsoba;
        int visina;
        int kilaza;
        String krvnaGrupa;

        bracniStatus = inputBoolean("Bracni status");
        brojUzdrzavanihOsoba = inputInteger("Broj uzdrzavanih osoba");
        visina = inputInteger("Visina");
        kilaza = inputInteger("Kilaza");

        do {
            krvnaGrupa = inputText("Krvna grupa");

            if (!Validator.isBloodType(krvnaGrupa)) {
                exceptionFormatMsg("Krvna grupa", msgKrvnaGrupaFormat);
            }
        } while (!Validator.isBloodType(krvnaGrupa));

        OsobniDetalj osobniDetalji = new OsobniDetalj(bracniStatus, brojUzdrzavanihOsoba, visina, kilaza, krvnaGrupa);
        return osobniDetalji;
    }

    public static Profesija unosProfesija() {
        String naziv;
        BigDecimal godisnjiPrihod;

        do {
            naziv = inputText("Naziv");
        } while (!Validator.isWord(naziv));

        godisnjiPrihod = inputBigDecimal("Godisnji prihod");

        Profesija profesija = new Profesija(naziv, godisnjiPrihod);
        return profesija;
    }

    public static Lifestyle unosLifestyle() {
        boolean vegetarijanac;
        boolean pusac;
        int avgBrojCigareta = 0;
        boolean alkoholnaPica;
        int avgBrojAlkoholnihPica = 0;
        int avgBrojKava = 0;
        int avgBrojSlatkihPica = 0;
        boolean redovniObroci;
        boolean kucnaKuhinja;

        vegetarijanac = inputBoolean("Vegetarijanac");
        pusac = inputBoolean("Pusac");

        if (pusac) {
            avgBrojCigareta = inputInteger("Prosjecan broj cigareta");
        }
        alkoholnaPica = inputBoolean("Konzumiranje alkoholnih pica");
        if (alkoholnaPica) {
            avgBrojAlkoholnihPica = inputInteger("Prosjecan broj alkoholnih pica");
        }
        avgBrojKava = inputInteger("Prosjecan broj kava");
        avgBrojSlatkihPica = inputInteger("Prosjecan broj slatkih pica");
        redovniObroci = inputBoolean("Redovni obroci");
        kucnaKuhinja = inputBoolean("Kucna kuhinja");

        Lifestyle lifestyle = new Lifestyle(vegetarijanac, pusac, avgBrojCigareta, alkoholnaPica, avgBrojAlkoholnihPica, avgBrojKava, avgBrojSlatkihPica, redovniObroci, kucnaKuhinja);
        return lifestyle;
    }

    public static OsnovnaPrituzba unosOsnovnaPrituzba() {
        String izjava;
        String prijasnjiTretmani;
        boolean bolnickoLjecenje;

        izjava = inputText("Izjava");
        prijasnjiTretmani = inputText("Prijasnji tretmani");
        bolnickoLjecenje = inputBoolean("Bolnicko ljecenje");

        OsnovnaPrituzba osnovnaPrituzba = new OsnovnaPrituzba(izjava, prijasnjiTretmani, bolnickoLjecenje);
        return osnovnaPrituzba;
    }

    public static MedicinskiDetalji unosMedicinskiDetalji() {
        boolean dijabeticar;
        boolean hipertenzivan;
        String srcanoStanje;
        String respiratornoStanje;
        String probavnoStanje;
        String ortopedskoStanje;
        String misicnoStanje;
        String neuroloskoStanje;
        String alergije;
        String lijekoviNuspojave;
        String prijasnjeOperacije;

        dijabeticar = inputBoolean("Dijabeticar");
        hipertenzivan = inputBoolean("Hipertenzivan");
        srcanoStanje = inputText("Srcano stanje");
        respiratornoStanje = inputText("Respiratorno stanje");
        probavnoStanje = inputText("Probavno stanje");
        ortopedskoStanje = inputText("Ortopedsko stanje");
        misicnoStanje = inputText("Misicno stanje");
        neuroloskoStanje = inputText("Neurolosko stanje");
        alergije = inputText("Alergije");
        lijekoviNuspojave = inputText("Lijekovi koji izazivaju nuspojave");
        prijasnjeOperacije = inputText("Prijasnje operacije");

        MedicinskiDetalji medicinskiDetalji = new MedicinskiDetalji(dijabeticar, hipertenzivan, srcanoStanje, respiratornoStanje, probavnoStanje, ortopedskoStanje, misicnoStanje, neuroloskoStanje, alergije, lijekoviNuspojave, prijasnjeOperacije);
        return medicinskiDetalji;
    }

    public static CRF unosCRF() {
        Osoba osoba;
        Date datumRodjenja;
        char spol;
        Kontakt kontakt;
        Rodbina rodbina;
        OsobniDetalj osobniDetalj;
        Profesija profesija;
        Lifestyle lifestyle;
        OsnovnaPrituzba osnovnaPrituzba;
        MedicinskiDetalji medicinskiDetalji;
        Date datumRegistracije;

        System.out.println("Ispis");
        osoba = unosOsoba();
        datumRodjenja = inputDate("Datum rodjenja");
        
        do {
            spol = inputCharacter("Spol", "Spol");
            
            if (!Validator.isSex(spol)) {
                exceptionFormatMsg("Spol", msgSpolFormat);
            }
        } while (!Validator.isSex(spol));

        kontakt = unosKontakt();
        rodbina = unosRodbina();
        osobniDetalj = unosOsobniDetalj();
        profesija = unosProfesija();
        lifestyle = unosLifestyle();
        osnovnaPrituzba = unosOsnovnaPrituzba();
        medicinskiDetalji = unosMedicinskiDetalji();
        datumRegistracije = getCurentDate();
        
        CRF crf = new CRF(osoba,datumRodjenja,spol,kontakt,rodbina,osobniDetalj,profesija,lifestyle,osnovnaPrituzba,medicinskiDetalji,datumRegistracije);
        return crf;
    }
    
    public static void unosMRF(){
        
    }

    public static void exceptionFormatMsg(String polje, String format) {
        System.out.println("Pogršan format u polju:" + format);
        System.out.println("Polje zahtjeva format:" + format);
    }

    public static String inputText(String polje) {
        System.out.print(polje + ":");
        String unos = sc.nextLine();
        return unos;
    }
    
    public static char inputCharacter(String polje,String format){
        System.out.print(polje + ":");
        char slovo = sc.next().charAt(0);
        return slovo;
    }

    public static int inputInteger(String polje) {
        System.out.print(polje + ":");
        int broj = -1;
        boolean valid;

        do {
            valid = true;
            if (sc.hasNextInt()) {
                broj = sc.nextInt();
            } else {
                valid = false;
                exceptionFormatMsg(polje, msgBrojFormat);
            }
        } while (!valid);
        return broj;
    }

    public static Date inputDate(String polje) {
        int dan;
        int mjesec;
        int godina;
        System.out.println(polje);

        do {
            dan = inputInteger("Dan");
            if (dan < 0 || dan > 31) {
                exceptionFormatMsg("Dan", msgDatumDanFormat);
            }
        } while (dan < 0 || dan > 31);

        do {
            mjesec = inputInteger("Mjesec");

            if (mjesec < 0 || mjesec > 12) {
                exceptionFormatMsg("Mjesec", msgDatumMjesecFormat);
            }
        } while (mjesec < 0 || mjesec > 12);

        do {
            godina = inputInteger("Godina");

            if (godina < 1900 || godina > 2019) {
                exceptionFormatMsg("Godina", msgDatumGodinaFormat);
            }
        } while (godina < 1900 || godina > 2019);

        Date datum = new Date(godina - 1900, mjesec - 1, dan);
        return datum;
    }

    public static boolean inputBoolean(String polje) {
        System.out.println(polje + "(" + msgBoolFormat + "):");
        String unos;

        do {
            unos = sc.nextLine();

            if (!unos.equals("D") && !unos.equals("N")) {
                exceptionFormatMsg(polje, msgBoolFormat);
            }
        } while (!unos.equals("D") && !unos.equals("N"));

        return unos.toUpperCase().equals("D");
    }

    private static BigDecimal inputBigDecimal(String polje) {
        System.out.print(polje + ":");
        BigDecimal broj = new BigDecimal(0);

        boolean valid;

        do {
            valid = true;
            if (sc.hasNextBigDecimal()) {
                broj = sc.nextBigDecimal();
            } else {
                valid = false;
                exceptionFormatMsg(polje, msgBrojFormat);
            }
        } while (!valid);
        return broj;
    }
    
    public static Date getCurentDate(){
        Date date = new Date();
        return date;
    }
}
