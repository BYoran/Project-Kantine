import java.util.LinkedList;

/**
 * class KassaRij
 * 
 * @author Lucas Wagenaar & Bjorn Smit
 * @version 27-05-2020
 */

public class KassaRij {

    private LinkedList<Dienblad> klantenRij;
    private int aantalKlanten;

    /**
     * Constructor
     */
    public KassaRij() {
        klantenRij = new LinkedList<>();
    }

    /**
     * Persoon sluit achter in de rij aan
     *
     * @param klant
     */
    public void sluitAchteraan(Dienblad klant) {
        klantenRij.add(klant);
    }

    /**
     * Indien er een rij bestaat, de eerste klant uit de rij verwijderen en retourneren. Als er
     * niemand in de rij staat geeft deze null terug.
     *
     * @return Eerste klant in de rij of null
     */
    public Dienblad eerstePersoonInRij() {
        if (erIsEenRij()) {
            Dienblad eerstVolgendeKlant = klantenRij.get(0);
            klantenRij.remove(0);
            return eerstVolgendeKlant;
        } else {
            return null;
        }
    }

    /**
     * Methode kijkt of er personen in de rij staan.
     *
     * @return Of er wel of geen rij bestaat
     */
    public boolean erIsEenRij() {
        aantalKlanten = klantenRij.size();
        if (aantalKlanten >= 2) {
            return true;
        } else {
            return false;
        }
    }
}