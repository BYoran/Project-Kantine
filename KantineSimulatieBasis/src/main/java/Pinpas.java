/**
 * class Pinpas
 * 
 * @author Bjorn Smit
 * @version 11-06-2020
 */

public class Pinpas extends Betaalwijze {

    private double kredietlimiet;

    /**
     * Methode om kredietlimiet te zetten
     *
     * @param kredietlimiet
     */
    public void setKredietLimiet(double kredietlimiet) {
        this.kredietlimiet = kredietlimiet;
    }

    /**
     * Methode om betaling af te handelen
     */
    public boolean betaal(double tebetalen) {
        if (saldo > tebetalen && tebetalen < kredietlimiet) {
            return false;
        } else {
            setSaldo(saldo - tebetalen);
            return true;
        }
    }
}