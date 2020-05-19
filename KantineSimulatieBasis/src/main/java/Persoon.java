public class Persoon {

    private int bsn;
    private String voornaam;
    private String achternaam;
    private Datum geboortedatum;
    private char geslacht;

    public Persoon(int bsn, String voornaam, String achternaam, Datum geboortedatum, char geslacht) {
        this.bsn = bsn;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
        if (geslacht != 'M' || geslacht != 'V') {
            this.geslacht = '0';
        } else {
            this.geslacht = geslacht;
        }
    }
    
    public Persoon() {
         bsn = 0;
         voornaam = "";
         achternaam = "";
         geboortedatum = null;
         geslacht = '0';
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
        } else if (geslacht == '0') {
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
            this.geslacht = '0';
        }
    }

    @Override
    public String toString() {
        return "BSN: " + bsn + "/n" + "Voornaam: " + voornaam + "/n" + "Achternaam: " + achternaam + "/n" + "Geboortedatum: " + getGeboortedatum() + "/n" + "Geslacht: " + getGeslacht();
    }
}