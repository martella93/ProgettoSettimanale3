package epicode.dao;

import epicode.entity.Loan;
import epicode.entity.User;

import javax.persistence.*;
import java.util.List;

public class UserDao {

    private EntityManager em;

    public UserDao(EntityManager em) {
        this.em = em;
    }

    public void save(User user){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(user);
        et.commit();
    }

    public User getByNumeroTessera(String numeroTessera) {
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.numeroTessera = :numeroTessera", User.class);
            query.setParameter("numeroTessera", numeroTessera);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public User getById(int id){
        return em.find(User.class, id);
    }

    public void delete(int id){
        EntityTransaction et = em.getTransaction();
        et.begin();
        User user = getById(id);

        if (user != null){
            em.remove(user);
        } else {
            System.out.println("Utente non trovato");
        }
        et.commit();
    }

    public List<Loan> getPrestitiByNumeroTessera(String numeroTessera) {
        User user = getByNumeroTessera(numeroTessera);
        if (user != null) {
            try {
                TypedQuery<Loan> query = em.createQuery("SELECT l FROM Loan l WHERE l.user = :user AND l.dataRestituzioneEffettiva IS NULL", Loan.class);
                query.setParameter("user", user);
                return query.getResultList();
            } catch (NoResultException e) {
                return null;
            }
        } else {
            System.out.println("Utente non trovato.");
            return null;
        }
    }
}
