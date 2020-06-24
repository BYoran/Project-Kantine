/**
 * class Docent
 * 
 * @author Lucas Wagenaar & Bjorn Smit
 * @version 11-06-2020
 */

public class Docent extends Persoon implements KortingskaartHouder {

    private String afkorting;
    private String afdeling;

    public Docent(int bsn, String voornaam, String achternaam, Datum geboortedatum, char geslacht, String afkorting, String afdeling) {
        super(bsn, voornaam, achternaam, geboortedatum, geslacht);
        this.afkorting = afkorting;
        this.afdeling = afdeling;
    }

    public Docent() {
        
    }

    public String getAfkorting(){
        return afkorting;
    }

    public String getAfdeling(){
        return afdeling;
    }

    public void setAfkorting(String afkorting){
        this.afkorting = afkorting; 
    }

    public void setAfdeling(String afdeling){
        this.afdeling = afdeling;
    }

    @Override
    public String toString(){
        return super.toString() + "\n" + "Afkorting: " + getAfkorting() + "\n" + "Afdeling: " + getAfdeling();
    }

    @Override
    public double geefKortingsPercentage() {
        return 0.25;
    }

    @Override
    public boolean heeftMaximum() {
        return true;
    }

    @Override
    public double geefMaximum() {
        return 1.00;
    }
}