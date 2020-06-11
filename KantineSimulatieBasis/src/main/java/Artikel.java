/**
 * class Artikel
 * 
 * @author Bjorn Smit
 * @version 19-05-2020
 */

public class Artikel {

    private String naam;
    private double prijs;

    public Artikel(String naam, double prijs) {
        this.naam = naam;
        this.prijs = prijs;
    }

    public Artikel() {
        naam = "";
        prijs = (double) 0.0;
    }

    /**
	 * @return the naam
	 */
    public String getNaam() {
        return naam;
    }

    /**
	 * @return the prijs
	 */
    public double getPrijs() {
        return prijs;
    }

    /**
	 * @param naam the naam to set
	 */
    public void setNaam(String nieuweNaam) {
        naam = nieuweNaam;
    }

    /**
	 * @param prijs the prijs to set
	 */
    public void setPrijs(double nieuwePrijs) {
        prijs = nieuwePrijs;
    }

    @Override
    public String toString() {
        return "Naam: " + getNaam() + "\n" + "Prijs: " + getPrijs();
    }
}