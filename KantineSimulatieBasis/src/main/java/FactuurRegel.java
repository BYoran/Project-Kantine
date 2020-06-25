import javax.persistence.*;
import java.io.Serializable;

/**
 * Class FactuurRegel
 * 
 * @author Lucas Wagenaar
 * @version 24-06-2020
 */

@Entity
@Table(name = "factuurregel")
public class FactuurRegel implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "factuur")
    private Factuur factuur;
    
    @Embedded
    private Artikel artikel;
    
    public FactuurRegel() {

    }
    
    public FactuurRegel(Factuur factuur, Artikel artikel) {
        this.factuur = factuur;
        this.artikel = artikel;
    }
    
    /**
     * @return een printbare factuurregel
     */
    public String toString() {
        return artikel.getNaam() + " Totaal: €" + artikel.getPrijs();
    }
}