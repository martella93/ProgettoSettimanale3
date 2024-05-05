package epicode.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "libri")
@NamedQuery(name = "Book.findByAutore", query = "SELECT b FROM Book b WHERE b.autore = :autore")
public class Book extends Catalogo{

    private String autore;
    private String genere;

    public Book(int codiceISBN, String titolo, int annoDiPubblicazione, int numeroPagine, String autore, String genere) {
        super(codiceISBN, titolo, annoDiPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }

    public Book(){}

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    @Override
    public String toString() {
        return "Book{" +
                "codiceISBN=" + getCodiceISBN() +
                ", titolo='" + getTitolo() + '\'' +
                ", annoDiPubblicazione=" + getAnnoDiPubblicazione() +
                ", numeroPagine=" + getNumeroPagine() +
                ", autore='" + autore + '\'' +
                ", genere='" + genere + '\'' +
                '}';
    }

}
