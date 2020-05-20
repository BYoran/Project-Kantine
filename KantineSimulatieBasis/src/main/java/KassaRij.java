import java.util.ArrayList;
import java.util.LinkedList;

public class KassaRij {
    private ArrayList<Dienblad> klantenRij;
    /**
     * Constructor
     */
    public KassaRij() {
        klantenRij = new ArrayList<>();
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
        }
            return null;
    }
    

    /**
     * Methode kijkt of er personen in de rij staan.
     *
     * @return Of er wel of geen rij bestaat
     */
    public boolean erIsEenRij() {
        aantalKlanten = klanten.size();
        if (aantalKlanten >= 2) {
            return true;
        }
        else {
            return false;
        }
    }
}
