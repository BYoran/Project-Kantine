/**
 * class Kantine
 * 
 * @author Bjorn Smit & Lucas Wagenaar
 * @version 27-05-2020
 */

public class Kantine {

    private Kassa kassa;
    private KassaRij kassarij;
    private KantineAanbod kantineAanbod;

    private double [] prijzen = new double[] {1.25, 1.50, 2.00, 2.00};
    private int[] aantal = new int[] {3, 7, 11};

    /**
     * Constructor
     */
    public Kantine() {
        kassarij = new KassaRij();
        kassa = new Kassa(kassarij);
    }

    /**
     * In deze methode wordt een Persoon en Dienblad gemaakt en aan elkaar gekoppeld. Maak twee
     * Artikelen aan en plaats deze op het dienblad. Tenslotte sluit de Persoon zich aan bij de rij
     * voor de kassa.
     */
    public void loopPakSluitAan(Dienblad dienblad, String[] artikelnamen) {
        kantineAanbod = new KantineAanbod(artikelnamen, prijzen, aantal);
        setKantineAanbod(kantineAanbod);

        for(int i = 0; i < artikelnamen.length-1; i++) {
            dienblad.voegToe(kantineAanbod.getArtikel(artikelnamen[i]));
        }
    }
    
    /* public void loopPakSluitAan() {
        Persoon persoon = new Persoon();
        Dienblad dienblad = new Dienblad();
        dienblad.setKlant(persoon);
        Artikel artikel1 = new Artikel();
        Artikel artikel2 = new Artikel();
        dienblad.voegToe(artikel1);
        dienblad.voegToe(artikel2);
        kassarij.sluitAchteraan(dienblad);
    }*/

    /**
     * Deze methode handelt de rij voor de kassa af. 
     */
    public void verwerkRijVoorKassa() {
        while (kassarij.erIsEenRij()) {
            kassarij.eerstePersoonInRij();
        }
    }

    public Kassa getKassa() {
        return kassa;
    }

    public KantineAanbod getKantineAanbod() {
        return kantineAanbod;
    }

    public void setKantineAanbod(KantineAanbod kantineAanbod) {
        this.kantineAanbod = kantineAanbod;
    }

    /**
     * Deze methode telt het geld uit de kassa
     *
     * @return hoeveelheid geld in kassa
     */
/*    public double hoeveelheidGeldInKassa() {
        return kassa.hoeveelheidGeldInKassa();
    }

    /**
     * Deze methode geeft het aantal gepasseerde artikelen.
     *
     * @return het aantal gepasseerde artikelen
     */
/*    public int aantalArtikelen() {
        return kassa.aantalArtikelen();
    }

    /**
     * Deze methode reset de bijgehouden telling van het aantal artikelen en "leegt" de inhoud van
     * de kassa.
     */
/*    public void resetKassa() {
        kassa = new Kassa(kassarij);
        kassarij = new KassaRij();
    }
*/
}