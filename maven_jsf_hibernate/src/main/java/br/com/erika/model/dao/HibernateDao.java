package br.com.erika.model.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

public class HibernateDao<T> implements IDao<T>, Serializable {
	private static final long serialVersionUID = 1L;
	private Class<T> classe;
	private Session session;

	public HibernateDao(Class<T> classe, Session session) {
		super();
		this.classe = classe;
		this.session = session;
	}

	public void save(T entity) throws Exception {
		session.save(entity);

	}

	public void update(T entity) throws Exception {
		session.update(entity);

	}

	public void remove(T entity) throws Exception {
		session.delete(entity);

	}

	public void merge(T entity) throws Exception {
		session.merge(entity);

	}

	public T getEntity(Serializable id) throws Exception {
		T entity = (T) session.get(classe, id);
		return entity;
	}

	public T getEntitybyDetachedCriteria(DetachedCriteria criteria)
			throws Exception {
		T entity = (T) criteria.getExecutableCriteria(session).uniqueResult();
		return entity;
	}

	public List<T> getListDetachedCriteria(DetachedCriteria criteria)
			throws Exception {
		return criteria.getExecutableCriteria(session).list();
	}

	public List<T> getEntities() throws Exception {
		List<T> entities = (List<T>) session.createCriteria(classe).list();
		return entities;
	}

}
