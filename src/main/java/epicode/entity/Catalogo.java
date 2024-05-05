package epicode.entity;

import javax.persistence.*;
import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Catalogo {

    @Id
    @GeneratedValue
    private int codiceISBN;

    @Column(nullable = false)
    private String titolo;

    @Column(name = "anno_di_pubblicazione")
    private int annoDiPubblicazione;

    @Column(name = "numero_pagine")
    private int numeroPagine;

    @OneToMany(mappedBy = "catalogo")
    private List<Loan> loans;

    public Catalogo(int codiceISBN, String titolo, int annoDiPubblicazione, int numeroPagine) {
        this.codiceISBN = codiceISBN;
        this.titolo = titolo;
        this.annoDiPubblicazione = annoDiPubblicazione;
        this.numeroPagine = numeroPagine;
    }

    public Catalogo(){}

    public int getCodiceISBN() {
        return codiceISBN;
    }

    public void setCodiceISBN(int codiceISBN) {
        this.codiceISBN = codiceISBN;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getAnnoDiPubblicazione() {
        return annoDiPubblicazione;
    }

    public void setAnnoDiPubblicazione(int annoDiPubblicazione) {
        this.annoDiPubblicazione = annoDiPubblicazione;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    @Override
    public String toString() {
        return "Catalogo{" +
                "codiceISBN=" + codiceISBN +
                ", titolo='" + titolo + '\'' +
                ", annoDiPubblicazione=" + annoDiPubblicazione +
                ", numeroPagine=" + numeroPagine +
                '}';
    }
}
