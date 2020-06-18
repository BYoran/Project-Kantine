/**
 * class Pinpas
 * 
 * @author Bjorn Smit & Lucas Wagenaar
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
    public void betaal(double tebetalen) throws TeWeinigGeldException{
        if (saldo - tebetalen < kredietlimiet) {
            throw new TeWeinigGeldException("Deze persoon heeft niet genoeg geld ");
        } else {
            setSaldo(saldo - tebetalen);
        }
    }
}