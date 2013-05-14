package br.com.wryel.horas.entity.filter;

import java.io.Serializable;

public abstract class EntityFilter<Entity extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Integer firstResult;
	
	protected Integer maxResult;

	/**
	 * @return the firstResult
	 */
	public Integer getFirstResult() {
		return firstResult;
	}

	/**
	 * @param firstResult the firstResult to set
	 */
	public void setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
	}

	/**
	 * @return the maxResult
	 */
	public Integer getMaxResult() {
		return maxResult;
	}

	/**
	 * @param maxResult the maxResult to set
	 */
	public void setMaxResult(Integer maxResult) {
		this.maxResult = maxResult;
	}	
}
