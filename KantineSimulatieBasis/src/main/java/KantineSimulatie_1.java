/**
 * class KantineSimulatie_1
 * 
 * @author Bjorn Smit
 * @version 27-05-2020
 */

public class KantineSimulatie_1 {

    private Kantine kantine;

    private Dienblad dienblad;

    public static final int DAGEN = 7;

    private String[] artikelen = new String[] {"peer", "hamburger", "soep", "chocoladereep"};

    /**
     * Constructor
     */
    public KantineSimulatie_1() {
        kantine = new Kantine();
    }

    /**
     * Deze methode simuleert een aantal dagen in het
     * verloop van de kantine
     *
     * @param dagen
     */
    
     
     public void simuleer(int dagen) {

        // herhaal voor elke dag
        for (int i = 0; i < dagen; i++) {

            // per dag nu even vast 10 + i personen naar binnen
            // laten gaan, wordt volgende week veranderd...

            // for lus voor personen
            for (int j = 0; j < 10 + i; j++) {
                kantine.loopPakSluitAan(dienblad, artikelen);
            }
            // verwerk rij voor de kassa
            kantine.verwerkRijVoorKassa();
            // toon dagtotalen (artikelen en geld in kassa)
            //kantine.hoeveelheidGeldInKassa();
            //kantine.aantalArtikelen();
            // reset de kassa voor de volgende dag
            //kantine.resetKassa();
        }
    }

    /**
     * Start een simulatie
     */
    
     public static void main(String[] args) {
        KantineSimulatie_1 kantinesimulatie1 = new KantineSimulatie_1();
        int dagen;

        if (args.length == 0) {
            dagen = DAGEN;
        } else {
            dagen = Integer.parseInt(args[0]);
        }
        
        kantinesimulatie1.simuleer(dagen);
    }
    
}