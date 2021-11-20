package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;
import java.util.function.Consumer;

public class DAOImpl {
    private Session currentSession;
    private Transaction currentTransaction;

    public Session openCurrentSession() {
        currentSession = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public Session openCurrentSessionWithTransaction() {
        currentSession = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSessionWithTransaction() {
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

    private void executeTransaction(Consumer<Session> action) {
        openCurrentSessionWithTransaction();
        action.accept(currentSession);
        closeCurrentSessionWithTransaction();
    }

    public <T> T findById(Class<T> entityClass, int id) {
        openCurrentSession();
        T model = (T) getCurrentSession().get(entityClass, id);
        closeCurrentSession();
        return model;
    }

    public <T> void save(final T entity) {
        executeTransaction(currentSession -> currentSession.save(entity));
    }

    public <T> void update(final T entity) {
        executeTransaction(currentSession -> currentSession.update(entity));
    }

    public <T> void delete(final T entity) {
        executeTransaction(currentSession -> currentSession.delete(entity));
    }

    public <T> List<T> findAll(Class<T> entityClass) {
        openCurrentSession();
        List<T> modelList = (List<T>) getCurrentSession().createQuery("from " + entityClass.getName()).list();
        closeCurrentSession();
        return modelList;
    }
}
