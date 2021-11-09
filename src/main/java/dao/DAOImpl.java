package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class DAOImpl {
    private Session currentSession;
    private Transaction currentTransaction;

    public Session openCurrentSession() {
        currentSession = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentTransaction.commit();
        currentSession.close();
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public <T> T findById(Class<T> entityClass, int id) {
        return (T) getCurrentSession().get(entityClass, id);
    }

    public <T> void save(final T entity) {
        getCurrentSession().save(entity);
    }

    public <T> void update(final T entity) {
        getCurrentSession().update(entity);
    }

    public <T> void delete(final T entity) {
        getCurrentSession().delete(entity);
    }

    public <T> List<T> findAll(Class<T> entityClass) {
        return (List<T>) getCurrentSession().createQuery("from " + entityClass.getName()).list();
    }
}
