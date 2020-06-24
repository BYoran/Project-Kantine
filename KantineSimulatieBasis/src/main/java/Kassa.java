import java.util.Iterator;
import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * class Kassa
 * 
 * @author Lucas Wagenaar & Bjorn Smit
 * @version 24-06-2020
 */

public class Kassa {
    
    private int aantalArtikelenBijKassa;
    private double totaalKassa;
    private double totaalKorting;
    private EntityManager manager;

    /**
     * Constructor
     */
    public Kassa(KassaRij kassarij, EntityManager manager) {
        aantalArtikelenBijKassa = 0;
        totaalKassa = 0;
        this.manager = manager;
    }

    /**
     * Vraag het aantal artikelen en de totaalprijs op. Tel deze gegevens op bij de controletotalen
     * die voor de kassa worden bijgehouden. De implementatie wordt later vervangen door een echte
     * betaling door de persoon.
     *
     * @param klant die moet afrekenen
     */
    public void rekenAf(Dienblad klant) {
        LocalDate datum = LocalDate.now();
        Factuur factuur = new Factuur(klant, datum);
        Iterator<Artikel> it = klant.getDienblad();

        while (it.hasNext()) {
            //Artikel a = it.next();
            aantalArtikelenBijKassa++;
        }

        double totaalPrijs = factuur.getTotal();
        int aantalArtikelen = aantalArtikelenBijKassa;
        //double kortingDagaanbiedingen = 0;

        //Persoon persoon = klant.getKlant(); // de klant
        /*
        if (persoon instanceof KortingskaartHouder) {
            KortingskaartHouder klantMetKorting = (KortingskaartHouder) persoon; // casten

            if (klantMetKorting.heeftMaximum()) {
                if ((klantMetKorting.geefKortingsPercentage() * totaalPrijs) / 100 < klantMetKorting.geefMaximum()) {
                    totaalPrijs -= (klantMetKorting.geefKortingsPercentage() * totaalPrijs) / 100 + kortingDagaanbiedingen; // haal korting van het bedrag af
                } else {
                    totaalPrijs -= klantMetKorting.geefMaximum() + kortingDagaanbiedingen; // haal max van het bedrag af
                }
            } else {
                totaalPrijs -= (klantMetKorting.geefKortingsPercentage() * totaalPrijs) / 100 + kortingDagaanbiedingen; // haal korting van het bedrag af
            }
        }

        if (klant.getKlant() instanceof KortingskaartHouder) {
            KortingskaartHouder kortingskaart = (KortingskaartHouder) klant.getKlant();
            double prijsMetKorting = (1 - kortingskaart.geefKortingsPercentage()) * totaalPrijs;
            double korting = totaalPrijs - prijsMetKorting;
            if (kortingskaart.heeftMaximum() && korting > kortingskaart.geefMaximum()) {
                totaalPrijs -= kortingskaart.geefMaximum();
            } else {
                totaalPrijs = prijsMetKorting;
            }
        }
        */
        Persoon persoon = klant.getKlant();
        Betaalwijze betaalwijze = persoon.getBetaalwijze();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            betaalwijze.betaal(totaalPrijs);
            totaalKassa += totaalPrijs;
            manager.persist(factuur);

            transaction.commit();

            System.out.println(factuur.toString());
        } catch (TeWeinigGeldException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e + klant.getKlant().getVoornaam() + " " + klant.getKlant().getAchternaam());
            e.printStackTrace();
        }

        aantalArtikelenBijKassa = aantalArtikelen;
        totaalKorting = factuur.getKorting();
        totaalKassa += factuur.getTotal();
        /*
        boolean betaald = betaalwijze.betaal(totaalPrijs);

        if (betaald) {
            aantalArtikelenBijKassa += aantalArtikelen;
            totaalKassa += totaalPrijs;
        } else {
            System.out.println("Betaling mislukt!");
        }
        */
    }

    /**
     * Geeft het aantal artikelen dat de kassa heeft gepasseerd, vanaf het moment dat de methode
     * resetWaarden is aangeroepen.
     *
     * @return aantal artikelen
     */
    public int aantalArtikelen() {
        return aantalArtikelenBijKassa;
    }

    /**
     * Geeft het totaalbedrag van alle artikelen die de kass zijn gepasseerd, vanaf het moment dat
     * de methode resetKassa is aangeroepen.
     *
     * @return hoeveelheid geld in de kassa
     */
    public double hoeveelheidGeldInKassa() {
        return totaalKassa;
    }

    public double getTotaalHoeveelheidKorting() {
        return totaalKorting;
    }

    /**
     * reset de waarden van het aantal gepasseerde artikelen en de totale hoeveelheid geld in de
     * kassa.
     */
    public void resetKassa() {
        aantalArtikelenBijKassa = 0;
        totaalKassa = 0;
        totaalKorting = 0;
    }
}