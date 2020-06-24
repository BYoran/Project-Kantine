import java.util.Stack;
import java.util.Iterator;

/**
 * class Dienblad
 * 
 * @author Lucas Wagenaar & Bjorn Smit
 * @version 27-05-2020
 */

public class Dienblad {

    private Persoon klant;
    private Stack<Artikel> artikelen;

    /**
     * Constructor
     */
    public Dienblad() {
        this(null);
    }

    public Dienblad(Persoon klant) {
        this.klant = klant;
        this.artikelen = new Stack<>();
    }

    public void setKlant(Persoon klant) {
        this.klant = klant;
    } 

    public Persoon getKlant() {
        return klant;
    }

    /**
     * Methode om artikel aan dienblad toe te voegen
     *
     * @param artikel
     */
    public void voegToe(Artikel artikel) {
        artikelen.add(artikel);
    }

    /**
     * Methode om aantal artikelen op dienblad te tellen
     *
     * @return Het aantal artikelen
     */
    public int getAantalArtikelen() {
        return artikelen.size();
    }

    /**
     * Methode om de totaalprijs van de artikelen op dienblad uit te rekenen
     *
     * @return De totaalprijs
     */
    public double getTotaalPrijs() {
        double totaalPrijs = 0;
        for (Artikel a : artikelen) {
            totaalPrijs += a.getPrijs();
        }
        return totaalPrijs;
    }

    public Iterator<Artikel> getDienblad() {
        return artikelen.iterator();
    }
}