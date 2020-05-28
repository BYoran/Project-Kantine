import java.util.Iterator;
/**
 * class Kassa
 * 
 * @author Lucas Wagenaar
 * @version 19-05-2020
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
            totaalKassa += a.getPrijs();
            aantalArtikelenBijKassa++;
        }
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
        totaalKassa = 0;
        aantalArtikelenBijKassa = 0;
    }
}
