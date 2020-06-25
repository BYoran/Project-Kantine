/**
 * class Artikel
 * 
 * @author Bjorn Smit & Lucas Wagenaar
 * @version 18-06-2020
 */

public class Artikel {

    private String naam;
    private double prijs;
    private double korting;
    private double modifier;

    public Artikel(String naam, double prijs) {
        this.naam = naam;
        this.prijs = prijs;
        korting = 0;
    }

    public Artikel(String naam, double prijs, double korting) {
        this.naam = naam;
        this.prijs = prijs;
        this.korting = korting;
        modifier = 1;
        berekenMetKorting();
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

    public double getKorting() {
        return korting;
    }

    public void setKorting(double korting) {
        this.korting = korting;
    }

    public void berekenMetKorting(){
        this.prijs = this.prijs * (modifier - korting);
    }

    @Override
    public String toString() {
        return "Naam: " + getNaam() + "\n" + "Prijs: " + getPrijs();
    }
}