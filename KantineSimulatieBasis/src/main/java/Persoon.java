/**
 * class Persoon
 * 
 * @author Bjorn Smit
 * @version 11-06-2020
 */

public class Persoon {

    private int bsn;
    private String voornaam;
    private String achternaam;
    private Datum geboortedatum;
    private char geslacht;
    private Betaalwijze betaalwijze;

    public Persoon(int bsn, String voornaam, String achternaam, Datum geboortedatum, char geslacht) {
        this.bsn = bsn;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
        setGeslacht(geslacht);
    }

    public Persoon() {
        bsn = 0;
        voornaam = "";
        achternaam = "";
        geboortedatum = null;
        geslacht = 'O';
    }

    /**
     * @return the bsn
     */
    public int getBsn() {
        return bsn;
    }

    /**
     * @return the voornaam
     */
    public String getVoornaam() {
        return voornaam;
    }

    /**
     * @return the achternaam
     */
    public String getAchternaam() {
        return achternaam;
    }

    /**
     * @return the geboortedatum
     */
    public String getGeboortedatum() {
        String data = "";
        if (geboortedatum == null) {
            data = "Onbekend";
        } else {
            data = geboortedatum.getDatumAsString();
        }
        return data;
    }

    /**
     * @return the geslacht
     */
    public String getGeslacht() {
        String string = "";
        if (geslacht == 'M') {
            string = "Man";
        } else if (geslacht == 'V') {
            string = "Vrouw";
        } else if (geslacht == 'O') {
            string = "Onbekend";
        }
        return string;
    }

    /**
     * @param bsn the bsn to set
     */
    public void setBsn(int bsn) {
        this.bsn = bsn;
    }

    /**
     * @param voornaam the voornaam to set
     */
    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    /**
     * @param achternaam the achternaam to set
     */
    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    /**
     * @param geboortedatum the geboortedatum to set
     */
    public void setGeboortedatum(Datum geboortedatum) {
        this.geboortedatum = geboortedatum;
    }
    /**
     * @param geslacht the geslacht to set
     */
    public void setGeslacht(char geslacht) {
        if (geslacht == 'M') {
            this.geslacht = geslacht;
        } else if (geslacht == 'V') {
            this.geslacht = geslacht;
        } else {
            this.geslacht = 'O';
        }
    }

    public Betaalwijze getBetaalwijze() {
        return betaalwijze;
    }

    public void setBetaalwijze(Betaalwijze betaalwijze) {
        this.betaalwijze = betaalwijze;
    }

    @Override
    public String toString() {
        return "BSN: " + getBsn() + "\n" + "Voornaam: " + getVoornaam() + "\n" + "Achternaam: " + getAchternaam()
        + "\n" + "Geboortedatum: " + getGeboortedatum() + "\n" + "Geslacht: " + getGeslacht();
    }
}