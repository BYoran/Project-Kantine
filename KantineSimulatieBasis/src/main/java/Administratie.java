/**
 * class Administratie
 * 
 * @author Bjorn Smit
 * @version 04-06-2020
 */

public class Administratie {

    static final int DAYS_IN_WEEK = 7;

    private Administratie() {

    }

    /**
     * Deze methode berekent van de int array aantal de gemiddelde waarde
     *
     * @param aantal
     * @return het gemiddelde
     */
    public static double berekenGemiddeldAantal(int[] aantal) {
        double gemiddeld;
        switch (aantal.length) {
            case 0:
                gemiddeld = 0.0;
                break;
            case 1:
                gemiddeld = aantal[0];
                break;
            default:
                int totaal = aantal.length;
                int som = 0;
                for (int i : aantal) {
                    som += i;
                }
                gemiddeld = (double) som / totaal;
                break;
        }
        return gemiddeld;
    }   

    /**
     * Deze methode berekent van de double array omzet de gemiddelde waarde
     *
     * @param omzet
     * @return het gemiddelde
     */
    public static double berekenGemiddeldeOmzet(double[] omzet) {
        double gemiddeld;
        switch (omzet.length) {
            case 0:
                gemiddeld = 0.0;
                break;
            case 1:
                gemiddeld = omzet[0];
                break;
            default:
                int totaal = omzet.length;
                double som = 0;
                for (double i : omzet) {
                    som += i;
                }
                gemiddeld = som / totaal;
                break;
        }
        return gemiddeld;
    }

    /**
     * Methode om dagomzet uit te rekenen
     *
     * @param omzet
     * @return array (7 elementen) met dagomzetten
     */

    public static double[] berekenDagOmzet(double[] omzet) {
        double[] temp = new double[DAYS_IN_WEEK];
        for(int i = 0; i < DAYS_IN_WEEK; i++) {
            int j = 0;
            int dag = i;
            while (dag < omzet.length) {
                temp[i] += omzet[dag];
                j++;
                dag = i + DAYS_IN_WEEK * j;
            }
        }
        return temp;
    }
}
