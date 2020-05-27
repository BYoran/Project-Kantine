/**
 * class Datum
 * 
 * @author Bjorn Smit
 * @version 19-05-2020
 */

public class Datum {

	private int dag;
	private int maand;
	private int jaar;

	/**
	 * Constructor
	 */
	public Datum(int dag, int maand, int jaar) {
		if (bestaatDatum(dag, maand, jaar) == true) {
			this.dag = dag;
			this.maand = maand;
			this.jaar = jaar;
		} else {
			dag = 0;
			maand = 0;
			jaar = 0;
		}
	}

	public Datum() {
		dag = 0;
		maand = 0;
		jaar = 0;
	}

	/**
	 * @return the dag
	 */
	public int getDag() {
		return dag;
	}

	/**
	 * @return the maand
	 */
	public int getMaand() {
		return maand;
	}

	/**
	 * @return the jaar
	 */
	public int getJaar() {
		return jaar;
	}

	/**
	 * @param dag the dag to set
	 */
	public void setDag(int dag) {
		this.dag = dag;
	}

	/**
	 * @param maand the maand to set
	 */
	public void setMaand(int maand) {
		this.maand = maand;
	}

	/**
	 * @param jaar the jaar to set
	 */
	public void setJaar(int jaar) {
		this.jaar = jaar;
	}

	public boolean bestaatDatum(int dag, int maand, int jaar) {
		if (dag < 1 || dag > 31) {
			return false;
		}
		if (maand < 1 || maand > 12) {
			return false;
		}
		if (jaar < 1900 || jaar > 2100) {
			return false;
		}
		if (maand == 2) {
			if (((jaar % 4 == 0) && (jaar % 100 != 0)) || (jaar % 400 == 0)) {
				return (dag <= 29);
			} else {
				return (dag <= 28);
			}
		}
		if (maand == 4 || maand == 6 || maand == 9 || maand == 11) {
			return (dag <= 30);
		}
		return true;
	}

	/**
	 * Getter voor String weergave van datum
	 *
	 * @return Geboortedatum
	 */
	public String getDatumAsString() {
		return dag + "-" + maand + "-" + jaar;
	}
}