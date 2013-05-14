package br.com.wryel.horas.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.wryel.horas.entity.filter.EntityFilter;

public abstract class DAOImpl<Entity extends Serializable, Id extends Serializable, Filter extends EntityFilter<Entity>> implements DAO<Entity, Id, Filter> {

	@PersistenceContext
	protected EntityManager entityManager;
	
	protected final Class<Entity> entityClass;

	protected final Class<Filter> filterClass;
	
	public DAOImpl(Class<Entity> entityClass, Class<Filter> filterClass) {
		this.entityClass = entityClass;
		this.filterClass = filterClass;
	}	

	@Override
	public void insert(Entity entity) throws DAOException {
		entityManager.persist(entity);
		entityManager.flush();
	}

	@Override
	@SuppressWarnings("unchecked")
	public void update(Entity entity) throws DAOException {
		Id id = (Id) DAOHelper.getKey(entity);
		Entity entityToUpdate = get(id);
		if (entityToUpdate == null) {
			throw new DAOException("Não foi encontrado uma entidade " + entityClass.getName() + " com a chave " + id + " para atualização");
		}
		copyPropertiesBeforeSynchronize(entityToUpdate, entity);
		entityManager.merge(entityToUpdate);
		entityManager.flush();
	}

	public Entity get(Id id) {
		Entity entity = entityManager.find(entityClass, id);
		return entity;
	}

	@Override
	public void delete(Entity entity) throws DAOException {
		entityManager.remove(entity);
		entityManager.flush();
	}
	
	protected abstract String createSqlQuery(final Filter filter);

	protected String createSqlCountQuery(final String sql) {
		String countSql = DAOHelper.createCountSql(sql);
		return countSql;
	}
	
	protected void applyFilter(final TypedQuery<?> query, final Filter filter) {		
		if (filter.getFirstResult() != null) {			
			query.setFirstResult(filter.getFirstResult());
		}		
		if (filter.getMaxResult() != null) {
			query.setMaxResults(filter.getMaxResult());
		}
	}
	
	protected void copyPropertiesBeforeSynchronize(final Entity destino, final Entity origem) {
		DAOHelper.copyProperties(destino, origem);
	}


	@Override
	public List<Entity> list() {
		Filter filter = DAOHelper.createFilter(filterClass);
		List<Entity> result = list(filter);
		return result;
	}
	
	@Override
	public List<Entity> list(Filter filter) throws DAOException {
		TypedQuery<Entity> typedQuery = entityManager.createQuery(createSqlQuery(filter), entityClass);
		applyFilter(typedQuery, filter);
		List<Entity> result = typedQuery.getResultList();
		return result;
	}
	
	@Override
	public Long count(final Filter filter) {
		String sql = createSqlCountQuery(createSqlQuery(filter));
		TypedQuery<Long> typedQuery = entityManager.createQuery(sql, Long.class);
		applyFilter(typedQuery, filter);
		Long quantidadeDeRegistros = typedQuery.getSingleResult();
		return quantidadeDeRegistros;
	}
}
