package epicode.entity;

import epicode.enums.Periodicita;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "magazine")
public class Magazine extends Catalogo {

    @Enumerated(EnumType.STRING)
    private Periodicita periodicita;

    public Magazine(int codiceISBN, String titolo, int annoDiPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(codiceISBN, titolo, annoDiPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    public Magazine(){}


    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }


    @Override
    public String toString() {
        return "Magazine{" +
                "codiceISBN=" + getCodiceISBN() +
                ", titolo='" + getTitolo() + '\'' +
                ", annoDiPubblicazione=" + getAnnoDiPubblicazione() +
                ", numeroPagine=" + getNumeroPagine() +
                ", periodicita=" + periodicita +
                '}';
    }

}
