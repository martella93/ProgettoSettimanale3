package epicode.dao;

import epicode.entity.Catalogo;
import epicode.entity.Loan;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.List;

public class LoanDao {
    private EntityManager em;

    public LoanDao(EntityManager em) {
        this.em = em;
    }

    public void save(Loan loan){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(loan);
        et.commit();
    }

    public Loan getById(int id){
        return em.find(Loan.class, id);
    }

    public void delete(int id){
        EntityTransaction et = em.getTransaction();
        et.begin();
        Loan loan = getById(id);

        if (loan != null){
            em.remove(loan);
        } else {
            System.out.println("Prestito non trovato");
        }
        et.commit();
    }

    public List<Loan> findPrestitiScadutiENonRestituiti() {
        LocalDate currentDate = LocalDate.now();
        List<Loan> prestitiScadutiENonRestituiti = em.createQuery("SELECT l FROM Loan l WHERE l.dataRestituzionePrevista < :currentDate AND l.dataRestituzioneEffettiva IS NULL", Loan.class)
                .setParameter("currentDate", currentDate)
                .getResultList();

        if (prestitiScadutiENonRestituiti.isEmpty()) {
            System.out.println("Non ci sono prestiti scaduti e non ancora restituiti.");
        } else {
            for (Loan prestito : prestitiScadutiENonRestituiti) {
                System.out.println(prestito);
            }
        }

        return prestitiScadutiENonRestituiti;
    }



}
