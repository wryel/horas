package br.com.wryel.horas.dao;

import java.io.Serializable;
import java.util.List;

import br.com.wryel.horas.entity.filter.EntityFilter;

public interface DAO<Entity extends Serializable, Id extends Serializable, Filter extends EntityFilter<Entity>> {
	
	public Entity get(Id id) throws DAOException;
	
	public void insert(Entity entity) throws DAOException;
	
	public void update(Entity entity) throws DAOException;
	
	public void delete(Entity entity) throws DAOException;
	
	public List<Entity> list() throws DAOException;
	
	public List<Entity> list(Filter filter) throws DAOException;
	
	public Long count(Filter filter) throws DAOException;
}
