package epicode.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "utenti")
public class User {

    @Id
    @GeneratedValue
    private int id;

    private String nome;
    private String cognome;

    @Column(name = "anno_di_nascita")
    private LocalDate annoDiNascita;

    @Column(name = "numero_tessera")
    private String numeroTessera;

    @OneToMany(mappedBy = "user")
    private List<Loan> loans;

    public User(int id, String nome, String cognome, LocalDate annoDiNascita, String numeroTessera) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.annoDiNascita = annoDiNascita;
        this.numeroTessera = numeroTessera;
    }

    public User(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getAnnoDiNascita() {
        return annoDiNascita;
    }

    public void setAnnoDiNascita(LocalDate annoDiNascita) {
        this.annoDiNascita = annoDiNascita;
    }

    public String getNumeroTessera() {
        return numeroTessera;
    }

    public void setNumeroTessera(String numeroTessera) {
        this.numeroTessera = numeroTessera;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }
}
