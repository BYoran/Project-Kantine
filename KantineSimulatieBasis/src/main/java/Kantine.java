import javax.persistence.EntityManager;

/**
 * class Kantine
 * 
 * @author Bjorn Smit & Lucas Wagenaar
 * @version 24-06-2020
 */

public class Kantine {

    private Kassa kassa;
    private KassaRij kassarij;
    private KantineAanbod kantineaanbod;
    private EntityManager manager;

    /**
     * Constructor
     */
    public Kantine(EntityManager manager) {
        kassarij = new KassaRij();
        kassa = new Kassa(kassarij, manager);
        this.manager = manager;
    }

    public Kantine() {
        kassarij = new KassaRij();
        kassa = new Kassa(kassarij, manager);
    }

    /**
     * In deze methode wordt een Persoon en Dienblad gemaakt en aan elkaar gekoppeld. Maak twee
     * Artikelen aan en plaats deze op het dienblad. Tenslotte sluit de Persoon zich aan bij de rij
     * voor de kassa.
     */
    public void loopPakSluitAan(Dienblad dienblad, String[] artikelnamen) {
        for (int i = 0; i < artikelnamen.length; i++) {
            dienblad.voegToe(kantineaanbod.getArtikel(artikelnamen[i]));
        }
        kassarij.sluitAchteraan(dienblad);
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
            Dienblad klant = kassarij.eerstePersoonInRij();
            kassa.rekenAf(klant);
        }
    }

    public Kassa getKassa() {
        return kassa;
    }

    public KantineAanbod getKantineAanbod() {
        return kantineaanbod;
    }

    public void setKantineAanbod(KantineAanbod kantineaanbod) {
        this.kantineaanbod = kantineaanbod;
    }

    public KassaRij getKassarij() {
        return kassarij;
    }
/*
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