/**
 * class KantineMedewerker
 * 
 * @author Lucas Wagenaar & Bjorn Smit
 * @version 11-06-2020
 */

public class KantineMedewerker extends Persoon implements KortingskaartHouder {

    private int medewerkersNummer;
    private boolean toestemmingKassa;

    public KantineMedewerker (int bsn, String voornaam, String achternaam, Datum geboortedatum, char geslacht, int medewerkersNummer, boolean toestemmingKassa) {
        super(bsn, voornaam, achternaam, geboortedatum, geslacht);
        this.medewerkersNummer = medewerkersNummer;
        this.toestemmingKassa = toestemmingKassa;
    }

    public KantineMedewerker() {
        
    }

    public int getMedewerkersNummer() {
        return medewerkersNummer;
    }

    public boolean toestemmingKassa() {
        return toestemmingKassa;
    }

    public void setMedewerkersNummer(int medewerkersNummer) {
        this.medewerkersNummer = medewerkersNummer;
    }

    public void setToestemmingKassa(boolean toestemmingKassa) {
        this.toestemmingKassa = toestemmingKassa;
    }

    @Override
    public String toString(){
        return super.toString() + "\n" + "Medewerkersnummer: " + getMedewerkersNummer() + "\n"
                + "Toestemming om achter de kassa te werken: " + toestemmingKassa();
    }

    @Override
    public double geefKortingsPercentage() {
        return 0.35;
    }

    @Override
    public boolean heeftMaximum() {
        return false;
    }  

    @Override
    public double geefMaximum() {
        return 0;
    }
}