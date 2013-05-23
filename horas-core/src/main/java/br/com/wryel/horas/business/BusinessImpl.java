package br.com.wryel.horas.business;

import java.io.Serializable;
import java.util.List;

import br.com.wryel.horas.dao.DAO;
import br.com.wryel.horas.entity.filter.EntityFilter;

public abstract class BusinessImpl<Entity extends Serializable, Id extends Serializable, Filter extends EntityFilter<Entity>, EntityDAO extends DAO<Entity, Id, Filter>> implements Business<Entity, Id, Filter> {

	protected EntityDAO dao;
	
	protected final Class<Entity> entityClass;
	
	public BusinessImpl(Class<Entity> entityClass) {
		this.entityClass = entityClass;
	}
	
	@Override
	public Entity get(Id id) {
		Entity entity = dao.get(id);
		return entity;
	}

	@Override
	public void insert(Entity entity) throws BusinessException {
		dao.insert(entity);
	}

	@Override
	public void update(Entity entity) throws BusinessException {
		dao.update(entity);
	}

	@Override
	public void delete(Entity entity) throws BusinessException {
		dao.delete(entity);
	}

	@Override
	public List<Entity> list() {
		List<Entity> list = dao.list();
		return list;
	}
	
	@Override
	public List<Entity> list(Filter filter) {
		List<Entity> list = dao.list(filter);
		return list;
	}
	
	public abstract void setDAO(EntityDAO entityDAO);
}
