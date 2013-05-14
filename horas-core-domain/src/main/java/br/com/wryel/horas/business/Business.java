package br.com.wryel.horas.business;

import java.io.Serializable;
import java.util.List;

import br.com.wryel.horas.entity.filter.EntityFilter;

public interface Business<Entity extends Serializable, Id extends Serializable, Filter extends EntityFilter<Entity>> {
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Entity get(Id id);
	
	/**
	 * 
	 * @param entity
	 */
	public void insert(Entity entity) throws BusinessException;
	
	/**
	 * 
	 * @param entity
	 */
	public void update(Entity entity) throws BusinessException;
	
	/**
	 * 
	 * @param entity
	 */
	public void delete(Entity entity) throws BusinessException;
	
	/**
	 * 
	 * @return
	 */
	public List<Entity> list();
	
	/**
	 * 
	 * @param filter
	 * @return
	 */
	public List<Entity> list(Filter filter);
}
