package PL;

import DAL.Adresa;
import DAL.CRF;
import DAL.DataBase;
import DAL.Drzava;
import DAL.Grad;
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
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import jdk.nashorn.internal.ir.ContinueNode;

public class Inputs {

    private static DataBase db = new DataBase();
    private static Scanner sc = new Scanner(System.in);
    private static final String msgWordFormat = "rijec";
    private static final String msgWordWithOnlyNumbersFormat = "brojevi";
    private static final String msgOibFormat = "OIB(11 brojeva)";
    private static final String msgPostanskiBrojFormat = "postanski broj (brojevi bez razmaka)";
    private static final String msgKrvnaGrupaFormat = "krvna grupa (A+,A-,B+,B-,AB+,AB-,0+,0-)";
    private static final String msgBrojFormat = "broj";
    private static final String msgBoolFormat = "d/n";
    private static final String msgDatumDanFormat = "Dan (1-31)";
    private static final String msgDatumMjesecFormat = "Mjesec (1-12)";
    private static final String msgDatumGodinaFormat = "Godina (1900-2019)";
    private static final String msgSpolFormat = "(M|Z)";

    public static Osoba unosOsoba() {
        String ime;
        String prezime;
        String oib;

        do {
            ime = inputWord("Ime");

            if (!Validator.isWordSlova(ime)) {
                exceptionFormatMsg("IME", msgWordFormat);
            }
        } while (!Validator.isWordSlova(ime));

        do {
            prezime = inputWord("Prezime");

            if (!Validator.isWordSlova(prezime)) {
                exceptionFormatMsg("PREZIME", msgWordFormat);
            }
        } while (!Validator.isWordSlova(prezime));

        do {
            oib = inputWord("OIB");
            if (!Validator.isTextOIB(oib)) {
                exceptionFormatMsg("OIB", msgOibFormat);
            }
        } while (!Validator.isTextOIB(oib));

        Osoba osoba = new Osoba(ime, prezime, oib);
        return osoba;
    }

    public static Uredaj unosUredaj() {
        String telefonPosao;
        String telefonKucni;
        String mobitel;
        String pager;
        String fax;
        String email;

        System.out.println("UNOS PODATAKA O MOBILNIM UREDAJIMA");
        do {
            telefonPosao = inputText("Telefon posao");
            if (!Validator.isTextMobitel(telefonPosao)) {
                exceptionFormatMsg("Telefon posao", "Nesto");
            }
        } while (!Validator.isTextMobitel(telefonPosao));

        do {
            telefonKucni = inputText("Telefon kucni");
            if (!Validator.isTextMobitel(telefonKucni)) {
                exceptionFormatMsg("Telefon kucni", "Nesto");
            }
        } while (!Validator.isTextMobitel(telefonKucni));

        do {
            mobitel = inputText("Mobitel");
            if (!Validator.isTextMobitel(mobitel)) {
                exceptionFormatMsg("Mobitel", "Nesto");
            }
        } while (!Validator.isTextMobitel(mobitel));

        do {
            pager = inputText("Pager");
            if (!Validator.isTextMobitel(pager)) {
                exceptionFormatMsg("Pager", "Nesto");
            }
        } while (!Validator.isTextMobitel(pager));

        do {
            fax = inputText("Fax");
            if (!Validator.isTextMobitel(fax)) {
                exceptionFormatMsg("Fax", "Nesto");
            }
        } while (!Validator.isTextMobitel(fax));

        do {
            email = inputText("E-mail");
            if (!Validator.isTextEmail(email)) {
                exceptionFormatMsg("E-mail", "Nesto");
            }
        } while (!Validator.isTextEmail(email));

        Uredaj uredjaj = new Uredaj(telefonPosao, telefonKucni, mobitel, pager, fax, email);
        return uredjaj;
    }

    public static Adresa unosAdresa(String polje) {
        System.out.println(polje);
        String ulica;
        String kucniBroj;
        int gradId;

        Map<Integer, String> mapaDrzava = kreirajHashMapuDrzava();
        int drzavaId;

        do {
            ulica = inputText("Ulica");
            if (!Validator.isTextSlova(ulica)) {
                exceptionFormatMsg("Ulica", "Nesto");
            }
        } while (!Validator.isTextSlova(ulica));

        do {
            kucniBroj = inputWord("Kucni broj");
            if (!Validator.isTextKucniBroj(kucniBroj)) {
                exceptionFormatMsg("Kucni broj", "Nesto");
            }
        } while (!Validator.isTextKucniBroj(kucniBroj));

        do {
            ispisHashMape(mapaDrzava, "DRZAVE");
            drzavaId = inputInteger("DrzavaId");
            if (!mapaDrzava.containsKey(drzavaId)) {
                idNePostojiString();
            }
        } while (!mapaDrzava.containsKey(drzavaId));

        Map<Integer, String> mapaGradova = kreirajHashMapuGradova(drzavaId);

        do {
            ispisHashMape(mapaGradova, "Gradovi");
            gradId = inputInteger("GradId");
            if (!mapaDrzava.containsKey(gradId)) {
                idNePostojiString();
            }
        } while (!mapaDrzava.containsKey(gradId));

        Adresa adresa = new Adresa(ulica, kucniBroj, gradId);
        return adresa;
    }

    public static Kontakt unosKontakt() {
        Adresa trenutnaAdresa;
        Adresa stalnaAdresa;
        Uredaj uredaj;

        trenutnaAdresa = unosAdresa("TRENUTNA ADRESA");
        stalnaAdresa = unosAdresa("STALNA ADRESA");
        uredaj = unosUredaj();

        Kontakt kontak = new Kontakt(trenutnaAdresa, stalnaAdresa, uredaj);
        return kontak;
    }

    public static Rodbina unosRodbina() {
        Osoba osoba;
        String odnosSaPacijentom;
        Kontakt kontakt;

        System.out.println("UNOS PODATAKA O RODBINI");
        osoba = unosOsoba();

        do {
            odnosSaPacijentom = inputText("Odnos sa pacijentom");
            if (!Validator.isTextSlova(odnosSaPacijentom)) {
                exceptionFormatMsg("Odnos sa pacijentom", msgWordFormat);
            }
        } while (!Validator.isWordSlova(odnosSaPacijentom));

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

        System.out.println("UNOS OSOBNI DETALJI PACIJENTA");
        bracniStatus = inputBoolean("Bracni status");

        do {
            brojUzdrzavanihOsoba = inputInteger("Broj uzdrzavanih osoba");
            if (brojUzdrzavanihOsoba < 0 || brojUzdrzavanihOsoba > 10) {
                exceptionFormatMsg("Broj uzdrzavanih osoba", "broj izmedju 1 i 10");
            }
        } while (brojUzdrzavanihOsoba < 0 || brojUzdrzavanihOsoba > 10);

        do {
            visina = inputInteger("Visina (cm)");
            if (visina < 1 || visina > 220) {
                exceptionFormatMsg("Visina", "broj izmedju 1 i 220");
            }
        } while (visina < 0 || visina > 220);

        do {
            kilaza = inputInteger("Kilaza (kg)");
            if (kilaza < 0 || kilaza > 220) {
                exceptionFormatMsg("Kilaza", "broj izmedju 1 i 400");
            }
        } while (visina < 0 || visina > 220);

        do {
            krvnaGrupa = inputWord("Krvna grupa");

            if (!Validator.isTextBloodType(krvnaGrupa)) {
                exceptionFormatMsg("Krvna grupa", msgKrvnaGrupaFormat);
            }
        } while (!Validator.isTextBloodType(krvnaGrupa));

        OsobniDetalj osobniDetalji = new OsobniDetalj(bracniStatus, brojUzdrzavanihOsoba, visina, kilaza, krvnaGrupa);
        return osobniDetalji;
    }

    public static Profesija unosProfesija() {
        String naziv;
        BigDecimal godisnjiPrihod;

        System.out.println("UNOS PROFESIJE");
        do {
            naziv = inputText("Naziv");
            if (!Validator.isTextSlova(naziv)) {
                exceptionFormatMsg("Naziv profesije", "Rijeci");
            }
        } while (!Validator.isTextSlova(naziv));

        do {
            godisnjiPrihod = inputBigDecimal("Godisnji prihod");
            if (godisnjiPrihod.compareTo(BigDecimal.ZERO) > 0) {
                exceptionFormatMsg("Godisnji prihod", "Broj veci od 0");
            }
        } while (godisnjiPrihod.compareTo(BigDecimal.ZERO) > 0);

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

        System.out.println("UNOS LIFESTYLE");
        vegetarijanac = inputBoolean("Vegetarijanac");

        pusac = inputBoolean("Pusac");
        if (pusac) {
            do {
                avgBrojCigareta = inputInteger("Prosjecan broj cigareta");
                if (avgBrojCigareta > 100 || avgBrojCigareta < 0) {
                    exceptionFormatMsg("Prosjecan broj cigareta", msgOibFormat);
                }
            } while (avgBrojCigareta < 0 || avgBrojCigareta > 100);
        }

        alkoholnaPica = inputBoolean("Konzumiranje alkoholnih pica");
        if (alkoholnaPica) {
            do {
                avgBrojAlkoholnihPica = inputInteger("Prosjecan broj alkoholnih pica");
                if (avgBrojAlkoholnihPica < 0 || avgBrojAlkoholnihPica > 50) {
                    exceptionFormatMsg("Prosjecan broj alkoholnih pica", msgOibFormat);
                }
            } while (avgBrojAlkoholnihPica < 0 || avgBrojAlkoholnihPica > 50);
        }

        do {
            avgBrojKava = inputInteger("Prosjecan broj kava");
            if(avgBrojKava < 0 || avgBrojKava >20){
                exceptionFormatMsg("Prosjecan broj kava", msgOibFormat);
            }
        } while (avgBrojKava < 0 || avgBrojKava > 20);
        
        do {
            avgBrojSlatkihPica = inputInteger("Prosjecan broj slatkih pica");
            if(avgBrojSlatkihPica < 0 || avgBrojSlatkihPica >20){
                exceptionFormatMsg("Prosjecan broj slatkih pica", msgOibFormat);
            }
        } while (avgBrojSlatkihPica < 0 || avgBrojSlatkihPica > 20);
        
        redovniObroci = inputBoolean("Redovni obroci");
        kucnaKuhinja = inputBoolean("Kucna kuhinja");

        Lifestyle lifestyle = new Lifestyle(vegetarijanac, pusac, avgBrojCigareta, alkoholnaPica, avgBrojAlkoholnihPica, avgBrojKava, avgBrojSlatkihPica, redovniObroci, kucnaKuhinja);
        return lifestyle;
    }

    public static OsnovnaPrituzba unosOsnovnaPrituzba() {
        String izjava;
        String prijasnjiTretmani;
        boolean bolnickoLjecenje;

        System.out.println("UNOS OSNOVNE PRITUZBE");
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

        System.out.println("UNOS MEDICINSKIH DETALJA");
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

        System.out.println("UNOS PODATAKA O PACIJENTU");
        osoba = unosOsoba();
        datumRodjenja = inputDate("Datum rodjenja");

        do {
            spol = inputCharacter("Spol", "M/Z");

            if (!Validator.isCharSex(spol)) {
                exceptionFormatMsg("Spol", msgSpolFormat);
            }
        } while (!Validator.isCharSex(spol));

        kontakt = unosKontakt();
        rodbina = unosRodbina();
        osobniDetalj = unosOsobniDetalj();
        profesija = unosProfesija();
        lifestyle = unosLifestyle();
        osnovnaPrituzba = unosOsnovnaPrituzba();
        medicinskiDetalji = unosMedicinskiDetalji();
        datumRegistracije = getCurentDate();

        CRF crf = new CRF(osoba, datumRodjenja, spol, kontakt, rodbina, osobniDetalj, profesija, lifestyle, osnovnaPrituzba, medicinskiDetalji, datumRegistracije);
        return crf;
    }

    public static void unosMRF() {

    }

    public static String inputWord(String polje) {
        System.out.print(polje + ":");
        String unos = sc.nextLine();
        String trim = unos.trim();
        return trim;
    }

    public static String inputText(String polje) {
        System.out.print(polje + ":");
        String unos = sc.nextLine();
        return unos;
    }

    public static char inputCharacter(String polje, String format) {
        System.out.print(polje + "[" + format + "]:");
        char c = sc.next().charAt(0);
        sc.nextLine();
        return c;
    }

    public static int inputInteger(String polje) {

        int broj = 0;
        boolean valid;
        do {
            valid = true;
            try {
                System.out.print(polje + ":");
                broj = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException ex) {
                valid = false;
                exceptionFormatMsg(polje, msgBrojFormat);
            }
        } while (!valid);
        return broj;
    }

    public static boolean inputBoolean(String polje) {
        String unos;
        do {
            System.out.print(polje + "(" + msgBoolFormat + "):");
            unos = sc.nextLine();
            unos = unos.toUpperCase();
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
            try {
                broj = sc.nextBigDecimal();
            } catch (NumberFormatException ex) {
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

    public static Date getCurentDate() {
        Date date = new Date();
        return date;
    }

    public static void exceptionFormatMsg(String polje, String format) {
        System.out.println("Pogršan format u polju:" + polje);
        System.out.println("Polje zahtjeva format:" + format);
    }

    private static void prikazGradovaDrzave(int idDrzava) {
        try {
            List<Grad> listaGradova = db.getGradoviDrzave(idDrzava);
            for (Grad grad : listaGradova) {
                System.out.println(grad.toString());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void ispisHashMape(Map<Integer, String> mapa, String naslov) {
        System.out.println(naslov);
        System.out.println("ID\tNaziv");
        for (Map.Entry<Integer, String> entry : mapa.entrySet()) {
            System.out.println("[" + entry.getKey() + "]" + "\t" + entry.getValue().toString());
        }
    }

    private static Map<Integer, String> kreirajHashMapuDrzava() {
        Map<Integer, String> mapaDrzava = new HashMap<>();

        try {
            for (Drzava drzava : db.getDrzave()) {
                mapaDrzava.put(drzava.getId(), drzava.getNaziv());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return mapaDrzava;
    }

    private static Map<Integer, String> kreirajHashMapuGradova(int drzavaId) {
        Map<Integer, String> mapaGradova = new HashMap<>();

        try {
            for (Grad g : db.getGradoviDrzave(drzavaId)) {
                mapaGradova.put(g.getId(), g.getNaziv());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return mapaGradova;
    }

    private static void idNePostojiString() {
        System.out.println("ID NE POSOJI");
    }
}
