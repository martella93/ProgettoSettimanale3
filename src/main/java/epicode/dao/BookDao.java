package epicode.dao;

import epicode.entity.Book;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.List;

public class BookDao {

    private EntityManager em;

    public BookDao(EntityManager em) {
        this.em = em;
    }



    public void save(Book book){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(book);
        et.commit();
    }

    public Book getById(int id){
        return em.find(Book.class, id);
    }

    public void delete(int id){
        EntityTransaction et = em.getTransaction();
        et.begin();
        Book book = getById(id);

        if (book != null){
            em.remove(book);
        } else {
            System.out.println("Book non trovato");
        }
        et.commit();
    }

    public void deleteByIsbn(String isbn) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            int isbnInt = Integer.parseInt(isbn);
            Book book = em.createQuery("SELECT b FROM Book b WHERE b.codiceISBN = :isbn", Book.class)
                    .setParameter("isbn", isbnInt)
                    .getSingleResult();

            em.remove(book);
            System.out.println("Libro rimosso");
        } catch (NoResultException e) {
            System.out.println("Nessun libro trovato con ISBN: " + isbn);
        }
        et.commit();
    }

    public Book findByIsbn(Integer isbn) {
        try {
            return em.createQuery("SELECT b FROM Book b WHERE b.codiceISBN = :isbn", Book.class)
                    .setParameter("isbn", isbn)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Nessun libro trovato con ISBN: " + isbn);
            return null;
        }
    }


    public List<Book> findByAnnoDiPubblicazione(int annoDiPubblicazione) {
        List<Book> books = em.createQuery("SELECT b FROM Book b WHERE b.annoDiPubblicazione = :anno", Book.class)
                .setParameter("anno", annoDiPubblicazione)
                .getResultList();

        if (books.isEmpty()) {
            System.out.println("Nessun libro trovato per l'anno di pubblicazione: " + annoDiPubblicazione);
        }

        return books;
    }
    public List<Book> findByAutore(String autore) {
        return em.createNamedQuery("Book.findByAutore", Book.class)
                .setParameter("autore", autore)
                .getResultList();
    }

    public List<Book> findByTitoloContaining(String titolo) {
        return em.createQuery("SELECT b FROM Book b WHERE LOWER(b.titolo) LIKE LOWER(:titolo)", Book.class)
                .setParameter("titolo", "%" + titolo + "%")
                .getResultList();
    }




}
