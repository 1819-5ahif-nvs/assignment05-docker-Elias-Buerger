package at.htl.nvs.persistence;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public abstract class Repository<T> implements Serializable {

    @PersistenceContext
    protected EntityManager em;
    protected Class<T> genericClass;

    public Repository(Class<T> genericClass) {
        this.genericClass = genericClass;
    }

    public boolean create(T toCreate) {
        try {
            em.persist(toCreate);
            em.flush();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean update(T toUpdate) {
        try {
            em.merge(toUpdate);
            em.flush();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean delete(long id) {
        try {
            em.remove(em.getReference(genericClass, id));
            em.flush();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public T find(long id) {
        return em.find(genericClass, id);
    }

    public List<T> getAll() {
        List<T> result = em.createQuery("select t from " + genericClass.getTypeName() + " t", genericClass).getResultList();
        return result;
    }
}