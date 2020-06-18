import java.util.*;

/**
 * class KantineSimulatie_2
 * 
 * @author Lucas Wagenaar & Bjorn Smit
 * @version 04-06-2020
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

    /**
     * Constructor
     *
     */
    public KantineSimulatie_2() {
        kantine = new Kantine();
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
        ArrayList<Artikel> aanbod = new ArrayList<>();
        for(int i = 0; i < artikelnamen.length; i++) {
            aanbod.add(new Artikel(artikelnamen[i], artikelprijzen[i]));
        }

        // for lus voor dagen
        for(int i = 0; i < dagen; i++) {

            // bedenk hoeveel personen vandaag binnen lopen
            int aantalpersonen = getRandomValue(MIN_PERSONEN_PER_DAG, MAX_PERSONEN_PER_DAG);

            // laat de personen maar komen...
            for (int j = 0; j < aantalpersonen; j++) {

                // maak persoon en dienblad aan, koppel ze
                Persoon persoon;
                int kans = random.nextInt(100);

                if (kans == 1) {
                    persoon = new KantineMedewerker();
                } else if (kans > 1 && kans <= 11) {
                    persoon = new Docent();
                } else {
                    persoon = new Student();
                }

                Pinpas portemonnee = new Pinpas();
                portemonnee.setSaldo(getRandomValue(-5, 20));
                portemonnee.setKredietLimiet(getRandomValue(-20, 0));
                persoon.setBetaalwijze(portemonnee);

                Dienblad dienblad = new Dienblad(persoon);
                System.out.println(persoon.toString());

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

            // druk de dagtotalen af en hoeveel personen binnen
            // zijn gekomen
            //kantine.hoeveelheidGeldInKassa();
            int dag = i + 1;
            Kassa kassa = kantine.getKassa();

            System.out.println("\n" + "Dagtotalen:");
            System.out.println("Aantal personen in kantine voor dag " + dag + ": " + 100);
            System.out.println("Aantal artikelen dat kassa passeert voor dag " + dag + ": " + kassa.aantalArtikelen());
            System.out.println("Hoeveelheid geld in kassa voor dag " + dag + ": " + kassa.hoeveelheidGeldInKassa());

            omzet[i] += kassa.hoeveelheidGeldInKassa();
            aantalArtikelenPerDag[i] += kassa.aantalArtikelen();

            // reset de kassa voor de volgende dag
            //kantine.resetKassa();
            kassa.resetKassa();
        }
        System.out.println("\n" + "Administratie:");

        double gemiddeldAantal = Administratie.berekenGemiddeldAantal(aantalArtikelenPerDag);
        System.out.println("Gemiddelde aantal artikelen per dag: " + gemiddeldAantal);

        double gemideldeOmzet = Administratie.berekenGemiddeldeOmzet(omzet);
        System.out.println("Gemiddelde omzet: " + gemideldeOmzet);

        String[] weekDagen = new String[] { "maandag", "dinsdag", "woensdag", "donderdag", "vrijdag", "zaterdag", "zondag" };
        double[] dagOmzet = Administratie.berekenDagOmzet(omzet);

        for(int i = 0; i < weekDagen.length; i++) {
            System.out.println("Totale omzet van alle " + weekDagen[i] + "en: " +  dagOmzet[i]);
        }

        System.out.println("Aantal dagen gesimuleerd: " + dagen);
    }

    public static void main(String[] args) {
        int dagen;

        if (args.length == 0) {
            dagen = 7;
        }
        else {
            dagen = Integer.parseInt(args[0]);
        }

        KantineSimulatie_2 kantineSimulatie = new KantineSimulatie_2();
        kantineSimulatie.simuleer(dagen);
    }
}