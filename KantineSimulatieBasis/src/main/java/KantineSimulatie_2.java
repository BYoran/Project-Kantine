import java.util.*;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 * class KantineSimulatie_2
 * 
 * @author Lucas Wagenaar & Bjorn Smit
 * @version 18-06-2020
 */

public class KantineSimulatie_2 {
    
    // kantine
    private Kantine kantine;

    // kantineaanbod
    private KantineAanbod kantineaanbod;

    // random generator
    private Random random;

    // aantal artikelen
    private static final int AANTAL_ARTIKELEN = 4;

    // artikelen
    private static final String[] artikelnamen =
            new String[] {"Koffie", "Broodje pindakaas", "Broodje kaas", "Appelsap"};

    // prijzen
    private static double[] artikelprijzen = new double[] {1.50, 2.10, 1.65, 1.65};

    // minimum en maximum aantal artikelen per soort
    private static final int MIN_ARTIKELEN_PER_SOORT = 10;
    private static final int MAX_ARTIKELEN_PER_SOORT = 20;

    // minimum en maximum aantal personen per dag
    private static final int MIN_PERSONEN_PER_DAG = 50;
    private static final int MAX_PERSONEN_PER_DAG = 100;

    // minimum en maximum artikelen per persoon
    private static final int MIN_ARTIKELEN_PER_PERSOON = 1;
    private static final int MAX_ARTIKELEN_PER_PERSOON = 4;

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("KantineSimulatie_2");
    private EntityManager manager;

    /**
     * Constructor
     *
     */
    public KantineSimulatie_2() {
        manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        kantine = new Kantine(manager);
        random = new Random();
        int[] hoeveelheden =
                getRandomArray(AANTAL_ARTIKELEN, MIN_ARTIKELEN_PER_SOORT, MAX_ARTIKELEN_PER_SOORT);
        kantineaanbod = new KantineAanbod(artikelnamen, artikelprijzen, hoeveelheden);

        kantine.setKantineAanbod(kantineaanbod);
    }

    /**
     * Methode om een array van random getallen liggend tussen min en max van de gegeven lengte te
     * genereren
     *
     * @param lengte
     * @param min
     * @param max
     * @return De array met random getallen
     */
    private int[] getRandomArray(int lengte, int min, int max) {
        int[] temp = new int[lengte];
        for (int i = 0; i < lengte; i++) {
            temp[i] = getRandomValue(min, max);
        }

        return temp;
    }

    /**
     * Methode om een random getal tussen min(incl) en max(incl) te genereren.
     *
     * @param min
     * @param max
     * @return Een random getal
     */
    private int getRandomValue(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Methode om op basis van een array van indexen voor de array artikelnamen de bijhorende array
     * van artikelnamen te maken
     *
     * @param indexen
     * @return De array met artikelnamen
     */
    private String[] geefArtikelNamen(int[] indexen) {
        String[] artikelen = new String[indexen.length];

        for (int i = 0; i < indexen.length; i++) {
            artikelen[i] = artikelnamen[indexen[i]];

        }

        return artikelen;
    }

    /**
     * Deze methode simuleert een aantal dagen
     * in het verloop van de kantine
     *
     * @param dagen
     */
    public void simuleer(int dagen) {
        double[] omzet = new double[dagen];
        int[] aantalArtikelenPerDag = new int[dagen];

        // for lus voor dagen
        for (int i = 0; i < dagen; i++) {

            // bedenk hoeveel personen vandaag binnen lopen
            int aantalpersonen = getRandomValue(MIN_PERSONEN_PER_DAG, MAX_PERSONEN_PER_DAG);
/*
            // aantal artikelen met korting
            int aantalArtikelenKorting = getRandomValue(1, artikelnamen.length);

            String[] artikelenKortingLijst = artikelnamen;

            for (int k = 0; k < aantalArtikelenKorting; k++) {
                int randomIndex = getRandomValue(0, artikelnamen.length-1);

                while (artikelenKortingLijst[randomIndex] == null) {
                    randomIndex = getRandomValue(0, artikelnamen.length - 1);
                }

                String artikel = artikelenKortingLijst[randomIndex];
                Artikel artikelKorting = kantineaanbod.getArtikel(artikel);
                artikelKorting.setKorting(0.2);
                artikelenKortingLijst[randomIndex] = null;
            }
*/
            // laat de personen maar komen...
            for (int j = 0; j < aantalpersonen; j++) {

                // maak persoon en dienblad aan, koppel ze
                Persoon persoon;
                int kans = random.nextInt(100);

                if (kans == 1) {
                    persoon = new KantineMedewerker(123, "Jan", "Knuppel", new Datum(1, 1, 2001), 'M', 456, false);
                } else if (kans > 1 && kans <= 11) {
                    persoon = new Docent(456, "Evert", "Kok", new Datum(2, 2, 2002), 'M', "E.K.", "SCMI");
                } else {
                    persoon = new Student(789, "Hans", "Klok", new Datum(3, 3, 2003), 'M', 147, "ICT");
                }

                Pinpas portemonnee = new Pinpas();
                portemonnee.setSaldo(getRandomValue(-5, 20));
                portemonnee.setKredietLimiet(getRandomValue(-20, 0));
                persoon.setBetaalwijze(portemonnee);

                Dienblad dienblad = new Dienblad(persoon);
                System.out.println(dienblad.getKlant().toString());

                // en bedenk hoeveel artikelen worden gepakt
                int aantalartikelen = getRandomValue(MIN_ARTIKELEN_PER_PERSOON, MAX_ARTIKELEN_PER_PERSOON);

                // genereer de "artikelnummers", dit zijn indexen
                // van de artikelnamen
                int[] tepakken = getRandomArray(aantalartikelen, 0, AANTAL_ARTIKELEN - 1);

                // vind de artikelnamen op basis van
                // de indexen hierboven
                String[] artikelen = geefArtikelNamen(tepakken);

                // loop de kantine binnen, pak de gewenste
                // artikelen, sluit aan
                kantine.loopPakSluitAan(dienblad, artikelen);
            }

            // verwerk rij voor de kassa
            kantine.verwerkRijVoorKassa();
/*
            for (int l = 0; l < artikelnamen.length; l++) {
                kantineaanbod.getArtikel(artikelnamen[l]).setKorting(0.0);
            }
*/
            // druk de dagtotalen af en hoeveel personen binnen
            // zijn gekomen
            int dag = i + 1;
            Kassa kassa = kantine.getKassa();

            System.out.println("\n" + "Dagtotalen:");
            System.out.println("Aantal personen in kantine voor dag " + dag + ": " + aantalpersonen);
            System.out.println("Aantal artikelen dat kassa passeert voor dag " + dag + ": " + kassa.aantalArtikelen());
            System.out.println("Hoeveelheid geld in kassa voor dag " + dag + ": €" + kassa.hoeveelheidGeldInKassa());

            omzet[i] += kassa.hoeveelheidGeldInKassa();
            aantalArtikelenPerDag[i] += kassa.aantalArtikelen();

            // reset de kassa voor de volgende dag
            kassa.resetKassa();
        }

        System.out.println("\n" + "Administratie:");

        double gemiddeldAantal = Administratie.berekenGemiddeldAantal(aantalArtikelenPerDag);
        System.out.println("Gemiddelde aantal artikelen per dag: " + gemiddeldAantal);

        double gemiddeldeOmzet = Administratie.berekenGemiddeldeOmzet(omzet);
        System.out.println("Gemiddelde omzet: €" + gemiddeldeOmzet);

        String[] weekDagen = new String[] { "maandag", "dinsdag", "woensdag", "donderdag", "vrijdag", "zaterdag", "zondag" };
        double[] dagOmzet = Administratie.berekenDagOmzet(omzet);

        for (int i = 0; i < weekDagen.length; i++) {
            System.out.println("Totale omzet van alle " + weekDagen[i] + "en: €" +  dagOmzet[i]);
        }

        System.out.println("\n" + "Aantal dagen gesimuleerd: " + dagen);

        System.out.println("\n" + "SQL-queries:");

        Query totaleOmzet = manager.createQuery("SELECT SUM(totaal) FROM Factuur");
        System.out.println("Totale omzet: €" + totaleOmzet.getSingleResult());

        Query toegepasteKorting = manager.createQuery("SELECT SUM(korting) FROM Factuur");
        System.out.println("Toegepaste korting: €" + toegepasteKorting.getSingleResult());

        Query gemiddeldeOmzetPerFactuur = manager.createQuery("SELECT AVG(totaal) FROM Factuur");
        System.out.println("Gemiddelde omzet per factuur: €" + gemiddeldeOmzetPerFactuur.getSingleResult());

        Query toegepasteKortingPerFactuur = manager.createQuery("SELECT AVG(korting) FROM Factuur");
        System.out.println("Toegepaste korting per factuur: €" + toegepasteKortingPerFactuur.getSingleResult());

        Query top = manager.createQuery("SELECT f FROM Factuur f ORDER BY totaal", Factuur.class);
        top.setMaxResults(3);
        List<Factuur> result = top.getResultList();

        System.out.println("Top 3 van facturen met de hoogste omzet: ");
        for (int i = 0; i < 3; i++) {
            System.out.println((i + 1) + ". " + result.get(i));
        }
// opgave 5
        Query queryArtikelNamen = manager.createQuery("SELECT naam FROM factuurregel GROUP BY naam");
        List<String> artikelNamen = queryArtikelNamen.getResultList();

        Query queryprijsTotalenPerArtikel = manager.createQuery("SELECT sum(prijs) FROM factuurregel GROUP BY naam");
        List<Double> prijsTotalenPerArtikel = queryprijsTotalenPerArtikel.getResultList();

        Query querykortingTotalenPerArtikel = manager.createNativeQuery("SELECT sum(korting) FROM factuurregel GROUP BY naam");
        List<Double> kortingTotalenPerArtikel = querykortingTotalenPerArtikel.getResultList();

        System.out.println("Totalen en toegepaste kortingen per artikel: ");
        for (int i = 0; i < artikelNamen.size(); i++) {
            System.out.println(artikelNamen.get(i) + ": totaal: €" + prijsTotalenPerArtikel.get(i) + ", toegepaste korting: €" + kortingTotalenPerArtikel.get(i));
        }

        Query queryDag = manager.createNativeQuery("SELECT DAY(f.datum) FROM factuurregel AS fr LEFT OUTER JOIN factuur AS f ON f.id = fr.factuur GROUP BY DAY(f.datum), naam");
        List<Integer> dag = queryDag.getResultList();

        Query queryArtikelNamenPerDag = manager.createNativeQuery("SELECT fr.naam FROM factuurregel AS fr LEFT OUTER JOIN factuur AS f ON f.id = fr.factuur GROUP BY DAY(f.datum), naam");
        List<String> artikelNamenPerDag = queryArtikelNamenPerDag.getResultList();

        Query queryPrijsTotalenPerArtikelPerDag = manager.createNativeQuery("SELECT sum(fr.prijs) FROM factuurregel AS fr LEFT OUTER JOIN factuur AS f ON f.id = fr.factuur GROUP BY DAY(f.datum), naam");
        List<Double> prijsTotalenPerArtikelPerDag = queryPrijsTotalenPerArtikelPerDag.getResultList();

        Query queryKortingTotalenPerArtikelPerDag = manager.createNativeQuery("SELECT sum(fr.korting) FROM factuurregel AS fr LEFT OUTER JOIN factuur AS f ON f.id = fr.factuur GROUP BY DAY(f.datum), naam");
        List<Double> kortingTotalenPerArtikelPerDag = queryKortingTotalenPerArtikelPerDag.getResultList();

        System.out.println("Totalen en toegepaste kortingen per artikel, per dag: ");
        for (int i = 0; i < dag.size(); i++) {
            System.out.println("Dag " + dag.get(i) + ": " + artikelNamenPerDag.get(i) + ": totaal: €" + prijsTotalenPerArtikelPerDag.get(i) + ", toegepaste korting: €" + kortingTotalenPerArtikelPerDag.get(i));
        }

        Query queryPopulairArtikelenTopDrie = manager.createQuery("SELECT naam FROM factuurregel GROUP BY naam ORDER BY count(naam) LIMIT 3");
        List<String> populaireArtikelenTopDrie = queryPopulairArtikelenTopDrie.getResultList();
// populair
        System.out.println("Top 3 van meest populaire artikelen: ");
        for (int i = 0; i < 3; i++) {
            System.out.println((i + 1) + ". " + populaireArtikelenTopDrie.get(i));
        }

        Query queryOmzetArtikelenTopDrie = manager.createQuery("SELECT naam FROM factuurregel GROUP BY naam ORDER BY sum(prijs) DESC LIMIT 3");
        List<String> omzetArtikelenTopDrie = queryOmzetArtikelenTopDrie.getResultList();

        System.out.println("Top 3 van artikelen met hoogste omzet: ");
        for (int i = 0; i < 3; i++) {
            System.out.println((i + 1) + ". " + omzetArtikelenTopDrie.get(i));
        }
/*
        Query queryArtikelNamen = manager.createQuery("SELECT artikel_naam, sum(artikel_prijs), artikel_korting");
        List<String> artikelNamen = queryArtikelNamen.getResultList();
        System.out.println("Artikelen " + artikelNamen);

        Query queryTotaalPerDag = manager.createQuery("SELECT artikel_naam, sum(artikel.prijs), artikel_korting GROUP BY datum");
        List<String> totaalPerDag = queryTotaalPerDag.getResultList();
        System.out.println("Totaal per dag " + totaalPerDag);


        Query queryPopulairArtikelenTopDrie = manager.createQuery("SELECT artikel_naam FROM Artikel GROUP BY artikel_naam ORDER BY artikel_naam LIMIT 3");
        List<String> populaireArtikelenTopDrie = queryPopulairArtikelenTopDrie.getResultList();
        System.out.println("Meest populaire artikelen " + populaireArtikelenTopDrie);

        Query queryOmzetArtikelenTopDrie = manager.createQuery("SELECT artikel_naam FROM Artikel ORDER BY sum(artikel_prijs) DESC LIMIT 3");
        List<String> omzetArtikelenTopDrie = queryOmzetArtikelenTopDrie.getResultList();
        System.out.println("Meeste omzet artikelen " + omzetArtikelenTopDrie);
*/
        manager.close();
        ENTITY_MANAGER_FACTORY.close();
    }

    public static void main(String[] args) {
        int dagen;

        if (args.length == 0) {
            dagen = 7;
        } else {
            dagen = Integer.parseInt(args[0]);
        }

        KantineSimulatie_2 kantineSimulatie = new KantineSimulatie_2();
        kantineSimulatie.simuleer(dagen);
    }
}