package epicode.dao;

import epicode.Main;
import epicode.entity.Book;
import epicode.entity.Magazine;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.List;

public class MagazineDao {

    private EntityManager em;

    public MagazineDao(EntityManager em) {
        this.em = em;
    }

    public void save(Magazine magazine){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(magazine);
        et.commit();
    }

    public Magazine getById(int id){
        return em.find(Magazine.class, id);
    }


    public void delete(int id){
        EntityTransaction et = em.getTransaction();
        et.begin();
        Magazine magazine = getById(id);

        if (magazine != null){
            em.remove(magazine);
        } else {
            System.out.println("Magazine non trovato");
        }
        et.commit();
    }

    public void deleteByIsbn(String isbn) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            int isbnInt = Integer.parseInt(isbn);
            Magazine magazine = em.createQuery("SELECT m FROM Magazine m WHERE m.codiceISBN = :isbn", Magazine.class)
                    .setParameter("isbn", isbnInt)
                    .getSingleResult();

            em.remove(magazine);
            System.out.println("Magazine rimosso");
        } catch (NoResultException e) {
            System.out.println("Nessun Magazine trovato con ISBN: " + isbn);
        }
        et.commit();
    }

    public Magazine findByIsbn(Integer isbn) {
        try {
            return em.createQuery("SELECT m FROM Magazine m WHERE m.codiceISBN = :isbn", Magazine.class)
                    .setParameter("isbn", isbn)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Nessun magazine trovato con ISBN: " + isbn);
            return null;
        }
    }


    public List<Magazine> findByAnnoDiPubblicazione(int annoDiPubblicazione) {
        List<Magazine> magazines = em.createQuery("SELECT m FROM Magazine m WHERE m.annoDiPubblicazione = :anno",Magazine.class)
                .setParameter("anno", annoDiPubblicazione)
                .getResultList();

        if (magazines.isEmpty()) {
            System.out.println("Nessun libro trovato per l'anno di pubblicazione: " + annoDiPubblicazione);
        }

        return magazines;
    }


    public List<Magazine> findByTitoloContaining(String titolo) {
        return em.createQuery("SELECT m FROM Magazine m WHERE LOWER(m.titolo) LIKE LOWER(:titolo)", Magazine.class)
                .setParameter("titolo", "%" + titolo + "%")
                .getResultList();
    }



}
