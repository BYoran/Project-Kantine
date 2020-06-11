import java.util.Iterator;

/**
 * class Kassa
 * 
 * @author Lucas Wagenaar & Bjorn Smit
 * @version 11-06-2020
 */

public class Kassa {
    
    private int aantalArtikelenBijKassa = 0;
    private double totaalKassa = 0;

    /**
     * Constructor
     */
    public Kassa(KassaRij kassarij) {
        kassarij = new KassaRij();
    }

    /**
     * Vraag het aantal artikelen en de totaalprijs op. Tel deze gegevens op bij de controletotalen
     * die voor de kassa worden bijgehouden. De implementatie wordt later vervangen door een echte
     * betaling door de persoon.
     *
     * @param klant die moet afrekenen
     */
    public void rekenAf(Dienblad klant) {
        Iterator<Artikel> it = klant.getDienblad();
        while(it.hasNext()) {
            Artikel a = it.next();
            aantalArtikelenBijKassa++;
            totaalKassa += a.getPrijs();
        }

        int aantalArtikelen = aantalArtikelenBijKassa;
        double totaalPrijs = totaalKassa;

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

        Betaalwijze betaalwijze = klant.getKlant().getBetaalwijze();
        
        try {
            betaalwijze.betaal(totaalPrijs);
            totaalKassa += totaalPrijs;
            aantalArtikelenBijKassa += aantalArtikelen;
        }
        catch(TeWeinigGeldException e) {
            System.out.println(e + klant.getKlant().getVoornaam() + " " + klant.getKlant().getAchternaam());
        }
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

    /**
     * reset de waarden van het aantal gepasseerde artikelen en de totale hoeveelheid geld in de
     * kassa.
     */
    public void resetKassa() {
        aantalArtikelenBijKassa = 0;
        totaalKassa = 0;
    }
}