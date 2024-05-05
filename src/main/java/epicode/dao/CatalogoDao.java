package epicode.dao;



import epicode.entity.Catalogo;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

public class CatalogoDao {

    private EntityManager em;

    public CatalogoDao(EntityManager em) {
        this.em = em;
    }

    public void save(Catalogo catalogo){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(catalogo);
        et.commit();
    }

    public Catalogo getById(int id){
        return em.find(Catalogo.class, id);
    }

    public void delete(int id){
        EntityTransaction et = em.getTransaction();
        et.begin();
        Catalogo catalogo = getById(id);

        if (catalogo != null){
            em.remove(catalogo);
        } else {
            System.out.println("Prestito non trovato");
        }
        et.commit();
    }

}
