/**
 * class Artikel
 * 
 * @author Bjorn Smit
 * @version 19-05-2020
 */

public class Artikel {

    private String naam;
    private float prijs;

    /**
     * Declaratie is dat je bij een variable de naam en datatype opgeeft.
     * Initialisatie is dat je een variabele een waarde toekent.
     */

    public Artikel(String naam, float prijs) {
        this.naam = naam;
        this.prijs = prijs;
    }

    public Artikel() {
        naam = "";
        prijs = (float) 0.0;
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
    public float getPrijs() {
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
    public void setPrijs(float nieuwePrijs) {
        prijs = nieuwePrijs;
    }

    @Override
    public String toString() {
        return "Naam: " + naam + "/n" + "Prijs: " + prijs;
    }
}