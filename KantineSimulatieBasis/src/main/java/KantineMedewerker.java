public class KantineMedewerker extends Persoon {
    private int medewerkersNummer;
    private boolean toestemmingKassa;

public KantineMedewerker (int bsn, String voornaam, String achternaam, Datum geboortedatum, char geslacht, int medewerkersNummer, boolean toestemmingKassa) {
    super(bsn, voornaam, achternaam, geboortedatum, geslacht);
    this.medewerkersNummer = medewerkersNummer;
    this.toestemmingKassa = toestemmingKassa;
}

public int getMedewerkersNummer() {
    return medewerkersNummer;
}

public boolean toestemmingKassa() {
    return toestemmingKassa;
}

public void setMedewerkersNummer(int medewerkersNummer) {
    this.medewerkersNummer = medewerkersNummer;
}

public void setToestemmingKassa(boolean toestemmingKassa) {
    this.toestemmingKassa = toestemmingKassa;
}

@Override
public String toString(){
    return "kantineMedewerker";
}
}