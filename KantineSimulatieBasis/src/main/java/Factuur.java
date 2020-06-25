import java.time.LocalDate;
import java.io.Serializable;
import java.util.Iterator;
import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

/**
 * class Factuur
 * 
 * @author Bjorn Smit & Lucas Wagenaar
 * @version 24-06-2020
 */

@Entity
@Table(name = "factuur")
public class Factuur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "datum", nullable = false)
    private LocalDate datum;

    @Column(name = "korting", nullable = false)
    private double korting;

    @Column(name = "totaal", nullable = false)
    private double totaal;

    @OneToMany(targetEntity = FactuurRegel.class, cascade = CascadeType.ALL)
    private List<FactuurRegel> regels = new ArrayList<>();

    public Factuur() {
        totaal = 0;
        korting = 0;
    }

    public Factuur(Dienblad klant, LocalDate datum) {
        this();
        this.datum = datum;

        verwerkBestelling(klant);
    }

    private void verwerkBestelling(Dienblad klant) {
        Iterator<Artikel> it = klant.getDienblad();

        while (it.hasNext()) {
            Artikel a = it.next();
            totaal += a.getPrijs();
            regels.add(new FactuurRegel(this, a));
        }

        Persoon persoon = klant.getKlant();

        if (persoon instanceof KortingskaartHouder) {
            KortingskaartHouder klantMetKorting = (KortingskaartHouder) persoon;
            double kortingskaart = klantMetKorting.geefKortingsPercentage();
            double modifier = 1;
            modifier = modifier - kortingskaart;
            if (klantMetKorting.heeftMaximum()) {
                double max = klantMetKorting.geefMaximum();
                if (totaal * kortingskaart > max) {
                    korting = max;
                    totaal -= max;
                } else {
                    korting = totaal * kortingskaart;
                    totaal *= modifier;
                }
            } else {
                korting = totaal * kortingskaart;
                totaal *= modifier;
            }
        }
/*
        double totaalPrijs = 0;
        double kortingDagaanbiedingen = 0;

        while (it.hasNext()) {
            Artikel a = it.next();
            if (a.getKorting() > 0) {
                totaalPrijs += a.getPrijs();
                kortingDagaanbiedingen += a.getKorting();
            }
            regels.add(new FactuurRegel(this, a));
        }

        if (persoon instanceof KortingskaartHouder) {
            KortingskaartHouder klantMetKorting = (KortingskaartHouder) persoon; // casten

            if (klantMetKorting.heeftMaximum()) {
                if ((klantMetKorting.geefKortingsPercentage() * totaalPrijs) / 100 < klantMetKorting.geefMaximum()) {
                    totaalPrijs -= (klantMetKorting.geefKortingsPercentage() * totaalPrijs) / 100 + kortingDagaanbiedingen; // haal korting van het bedrag af
                } else {
                    totaalPrijs -= klantMetKorting.geefMaximum() + kortingDagaanbiedingen; // haal max van het bedrag af
                }
            } else {
                totaalPrijs -= (klantMetKorting.geefKortingsPercentage() * totaalPrijs) / 100 + kortingDagaanbiedingen; // haal korting van het bedrag af
            }
        }
        this.totaal = totaalPrijs;
        this.korting = kortingDagaanbiedingen;
        */
        /*
        while (it.hasNext()) {
            Artikel a = it.next();
            kortingDagaanbiedingen = a.getKorting();
            double artikelPrijs = a.getPrijs() - kortingDagaanbiedingen;

            if (persoon instanceof KortingskaartHouder && kortingDagaanbiedingen == 0) {
                KortingskaartHouder klantMetKorting = (KortingskaartHouder) persoon; // casten

                double artikelPrijsMetKorting = (1 - klantMetKorting.geefKortingsPercentage()) * artikelPrijs;
                this.korting = artikelPrijs - artikelPrijsMetKorting;

                if (klantMetKorting.heeftMaximum() && korting > klantMetKorting.geefMaximum()) {
                    artikelPrijs -= klantMetKorting.geefMaximum(); // haal max van het bedrag af
                } else {
                    artikelPrijs = artikelPrijsMetKorting; // haal korting van het bedrag af
                }    
            }
            totaalPrijs += artikelPrijs;
            this.korting += kortingDagaanbiedingen;
        }
        this.totaal = totaalPrijs;
        */
    }

    /**
     * @return het totaalbedrag
     */
    public double getTotal() {
        return totaal;
    }

    /**
     * @return de toegepaste korting
     */
    public double getKorting() {
        return korting;
    }

    /**
     * @return een printbaar bonnetje
     */
    public String toString() {
        String factuurRegels = "";

        for(FactuurRegel regel : regels){
            factuurRegels += regel.toString() + "\n";
        }

        return "Factuurdatum: " + datum + "\n" + "Artikelen: " + factuurRegels.trim() + 
                "\n" + "Korting: €" + getKorting() + "\n" + "Totaalbedrag: €" + getTotal();
    }
}