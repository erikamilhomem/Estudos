package br.com.erika.model.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;

/**
 * interface de persistência ao banco de dados
 * */
public interface IDao<T> {
	void save(T entity) throws Exception;

	void update(T entity) throws Exception;

	void remove(T entity) throws Exception;

	void merge(T entity) throws Exception;

	T getEntity(Serializable id) throws Exception;

	T getEntitybyDetachedCriteria(DetachedCriteria criteria) throws Exception;

	List<T> getEntities() throws Exception;

	List<T> getListDetachedCriteria(DetachedCriteria criteria) throws Exception;

}
