package epicode;

import epicode.dao.*;
import epicode.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogo");
        EntityManager em = emf.createEntityManager();

        UserDao userDao = new UserDao(em);
        LoanDao loanDao = new LoanDao(em);
        CatalogoDao catalogoDao = new CatalogoDao(em);
        BookDao bookDao = new BookDao(em);
        MagazineDao magazineDao = new MagazineDao(em);


        Scanner scanner = new Scanner(System.in);

        int scelta;
        do {
            System.out.println("Seleziona un'azione:");
            System.out.println("1. Aggiungi un utente");
            System.out.println("2. Aggiungi un libro");
            System.out.println("3. Aggiungi una rivista");
            System.out.println("4. Effettua un prestito");
            System.out.println("5. Elimina un libro");
            System.out.println("6. Elimina una rivista");
            System.out.println("7. Cerca un libro per ISBN");
            System.out.println("8. Cerca una rivista per ISBN");
            System.out.println("9. Cerca libri per anno di pubblicazione");
            System.out.println("10. Cerca riviste per anno di pubblicazione");
            System.out.println("11. Cerca libri per autore");
            System.out.println("12. Cerca libri per titolo");
            System.out.println("13. Cerca riviste per titolo");
            System.out.println("14. Visualizza elementi in prestito per un utente");
            System.out.println("15. Visualizza prestiti scaduti e non ancora restituiti");
            System.out.println("0. Esci");

            scelta = scanner.nextInt();

            switch (scelta) {
                case 1:
                    User nuovoUtente = new User();
                    System.out.println("Inserisci il nome dell'utente:");
                    nuovoUtente.setNome(scanner.next());
                    System.out.println("Inserisci il cognome dell'utente:");
                    nuovoUtente.setCognome(scanner.next());
                    System.out.println("Inserisci l'anno di nascita dell'utente (AAAA-MM-GG):");
                    nuovoUtente.setAnnoDiNascita(LocalDate.parse(scanner.next()));
                    System.out.println("Inserisci il numero di tessera dell'utente:");
                    nuovoUtente.setNumeroTessera(scanner.next());
                    userDao.save(nuovoUtente);
                    System.out.println("Utente aggiunto con successo!");
                    break;
                case 2:
                    Book nuovoLibro = new Book();
                    System.out.println("Inserisci il codice ISBN del libro:");
                    nuovoLibro.setCodiceISBN(scanner.nextInt());
                    System.out.println("Inserisci l'autore del libro:");
                    nuovoLibro.setAutore(scanner.next());
                    System.out.println("Inserisci il genere del libro:");
                    nuovoLibro.setGenere(scanner.next());
                    System.out.println("Inserisci il titolo del libro:");
                    nuovoLibro.setTitolo(scanner.next());
                    System.out.println("Inserisci l'anno di pubblicazione del libro:");
                    nuovoLibro.setAnnoDiPubblicazione(scanner.nextInt());
                    System.out.println("Inserisci il numero di pagine del libro:");
                    nuovoLibro.setNumeroPagine(scanner.nextInt());
                    bookDao.save(nuovoLibro);
                    System.out.println("Libro aggiunto con successo!");
                    break;
                case 3:
                    Magazine nuovaRivista = new Magazine();
                    System.out.println("Inserisci il codice ISBN della rivista:");
                    nuovaRivista.setCodiceISBN(scanner.nextInt());
                    System.out.println("Inserisci il titolo della rivista:");
                    nuovaRivista.setTitolo(scanner.next());
                    System.out.println("Inserisci il numero di pagine della rivista:");
                    nuovaRivista.setNumeroPagine(scanner.nextInt());
                    System.out.println("Inserisci l'anno di pubblicazione della rivista:");
                    nuovaRivista.setAnnoDiPubblicazione(scanner.nextInt());
                    magazineDao.save(nuovaRivista);
                    System.out.println("Rivista aggiunta con successo!");
                    break;
                case 4:
                    System.out.println("Effettua un prestito:");

// Ottenere il numero di tessera dell'utente
                    System.out.println("Inserisci il numero di tessera dell'utente:");
                    String numeroTessera = scanner.next();

// Ottenere l'ISBN del libro o della rivista
                    System.out.println("Inserisci l'ISBN del libro o della rivista:");
                    String isbn = scanner.next();

// Ottenere la data di prestito
                    System.out.println("Inserisci la data di prestito (AAAA-MM-GG):");
                    LocalDate loanDate = LocalDate.parse(scanner.next());

// Calcolare la data di restituzione prevista (esempio: 14 giorni dopo la data di prestito)
                    LocalDate returnDate = loanDate.plusDays(14);

// Ottenere l'utente basato sul numero di tessera
                    User user = userDao.getByNumeroTessera(numeroTessera);

                    if (user != null) {
                        // Creare un nuovo prestito
                        Loan prestito = new Loan();
                        prestito.setUser(user);
                        prestito.setIsbn(isbn);
                        prestito.setDataInizioPrestito(loanDate); // Imposta la data di inizio prestito
                        prestito.setDataRestituzionePrevista(returnDate); // Imposta la data di restituzione prevista

                        // Effettuare il prestito
                        loanDao.save(prestito);

                        System.out.println("Prestito effettuato con successo!");
                    } else {
                        System.out.println("Utente non trovato con il numero di tessera fornito.");
                    }

                    break;
                case 5:
                    System.out.println("Elimina un libro per ISBN:");
                    System.out.println("Inserisci l'ISBN del libro da eliminare:");
                    String isbnToDelete = scanner.next();
                    bookDao.deleteByIsbn(isbnToDelete);
                    break;
                case 6:
                    System.out.println("Elimina un magazine per ISBN:");
                    System.out.println("Inserisci l'ISBN del megazine da eliminare:");
                    String isbnToDelete2 = scanner.next();
                    magazineDao.deleteByIsbn(isbnToDelete2);
                    break;
                case 7:
                    System.out.println("Cerca un libro per ISBN:");
                    System.out.println("Inserisci l'ISBN del libro da cercare:");
                    String isbnToSearch = scanner.next();
                    int isbnValue = Integer.parseInt(isbnToSearch);
                    Book book = bookDao.findByIsbn(isbnValue);
                    System.out.println(book);
                    break;

                case 8:
                    System.out.println("Cerca un magazine per ISBN:");
                    System.out.println("Inserisci l'ISBN del magazine da cercare:");
                    String isbnToSearch2 = scanner.next();
                    int isbnValue2 = Integer.parseInt(isbnToSearch2);
                    Magazine magazine = magazineDao.findByIsbn(isbnValue2);
                    System.out.println(magazine);
                    break;
                case 9:
                    System.out.println("Cerca libri per anno di pubblicazione:");
                    System.out.println("Inserisci l'anno:");
                    int year = scanner.nextInt();
                    List<Book> booksByYear = bookDao.findByAnnoDiPubblicazione(year);
                    if (!booksByYear.isEmpty()) {
                        System.out.println("Libri pubblicati nell'anno " + year + ":");
                        for (Book book1 : booksByYear) {
                            System.out.println(book1);
                        }
                    } else {
                        System.out.println("Nessun libro trovato per l'anno " + year);
                    }
                    break;

                case 10:
                    System.out.println("Cerca magazine per anno di pubblicazione:");
                    System.out.println("Inserisci l'anno:");
                    int year1 = scanner.nextInt();

                    List<Magazine> magazineByYear = magazineDao.findByAnnoDiPubblicazione(year1);
                    if (!magazineByYear.isEmpty()) {
                        System.out.println("Libri pubblicati nell'anno " + year1 + ":");
                        for (Magazine magazine1 : magazineByYear) {
                            System.out.println(magazine1);
                        }
                    } else {
                        System.out.println("Nessun libro trovato per l'anno " + year1);
                    }
                    break;
                case 11:
                    System.out.println("Cerca libri per autore:");
                    System.out.println("Inserisci il nome dell'autore:");
                    scanner.nextLine();
                    String autore = scanner.nextLine().toLowerCase();
                    List<Book> booksByAutore = bookDao.findByAutore(autore);
                    if (!booksByAutore.isEmpty()) {
                        System.out.println("Libri dell'autore " + autore + ":");
                        for (Book book2 : booksByAutore) {
                            System.out.println(book2);
                        }
                    } else {
                        System.out.println("Nessun libro trovato per l'autore " + autore);
                    }
                    break;
                case 12:
                    System.out.println("Cerca libri per titolo contenente:");
                    System.out.println("Inserisci la stringa o lettera da cercare nel titolo:");
                    scanner.nextLine();
                    String searchString = scanner.nextLine().toLowerCase();
                    List<Book> booksByTitle = bookDao.findByTitoloContaining(searchString);
                    if (!booksByTitle.isEmpty()) {
                        System.out.println("Libri con titolo contenente '" + searchString + "':");
                        for (Book book1 : booksByTitle) {
                            System.out.println(book1);
                        }
                    } else {
                        System.out.println("Nessun libro trovato con titolo contenente '" + searchString + "'");
                    }
                    break;

                case 13:
                    System.out.println("Cerca magazine per titolo contenente:");
                    System.out.println("Inserisci la stringa o lettera da cercare nel magazine:");
                    scanner.nextLine();
                    String searchByString = scanner.nextLine().toLowerCase();
                    List<Magazine> magazineByTitle = magazineDao.findByTitoloContaining(searchByString);
                    if (!magazineByTitle.isEmpty()) {
                        System.out.println("Magazine con titolo contenente '" + searchByString + "':");
                        for (Magazine magazine2 : magazineByTitle) {
                            System.out.println(magazine2);
                        }
                    } else {
                        System.out.println("Nessun libro trovato con titolo contenente '" + searchByString + "'");
                    }
                    break;
                case 14:
//                    System.out.println("Cerca elementi in prestito per numero di tessera utente:");
//                    System.out.println("Inserisci il numero di tessera utente:");
//                    scanner.nextLine();
//                    String numeroTessera1 = scanner.nextLine();
//                    List<Catalogo> elementiInPrestito = loanDao.findElementiInPrestitoByNumeroTesseraUtente(numeroTessera1);
//                    if (!elementiInPrestito.isEmpty()) {
//                        System.out.println("Elementi in prestito per il numero di tessera " + numeroTessera1 + ":");
//                        for (Catalogo elemento : elementiInPrestito) {
//                            System.out.println(elemento);
//                        }
//                    } else {
//                        System.out.println("Nessun elemento trovato in prestito per il numero di tessera " + numeroTessera1);
//                    }
//                    break;
                    System.out.println("Cerca elementi in prestito per numero di tessera utente:");
                    System.out.println("Inserisci il numero di tessera utente:");
                    scanner.nextLine();
                    String numeroTessera1 = scanner.nextLine();

                    List<Loan> prestiti = userDao.getPrestitiByNumeroTessera(numeroTessera1);

                    if (prestiti != null && !prestiti.isEmpty()) {
                        System.out.println("Elementi in prestito per il numero di tessera " + numeroTessera1 + ":");
                        for (Loan prestito : prestiti) {
                            System.out.println(prestito); // Assicurati che la classe Loan abbia un metodo toString() appropriato
                        }
                    } else {
                        System.out.println("Nessun elemento trovato in prestito per il numero di tessera " + numeroTessera1);
                    }
                    break;

                case 15:
                    List<Loan> prestitiScadutiENonRestituiti = loanDao.findPrestitiScadutiENonRestituiti();
                    if (!prestitiScadutiENonRestituiti.isEmpty()) {
                        System.out.println("Prestiti scaduti e non restituiti:");
                        for (Loan prestito1 : prestitiScadutiENonRestituiti) {
                            System.out.println(prestito1);
                        }
                    } else {
                        System.out.println("Nessun prestito scaduto e non restituito trovato.");
                    }
                    break;

                case 0:
                    System.out.println("Arrivederci!");
                    break;
                default:
                    System.out.println("Scelta non valida. Riprova.");
                    break;
            }
        } while (scelta != 0);

    }
}


