/**
 * class Contant
 * 
 * @author Bjorn Smit & Lucas Wagenaar
 * @version 11-06-2020
 */

public class Contant extends Betaalwijze {
    /**
     * Methode om betaling af te handelen
     */
    
     public void betaal(double tebetalen) throws TeWeinigGeldException{
        if (saldo < tebetalen) {
            throw new TeWeinigGeldException("Deze persoon heeft niet genoeg geld");
        } else {
            setSaldo(saldo - tebetalen);
        }
    }
}