package br.com.wryel.horas.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.wryel.horas.exception.HorasRuntimeException;
import br.com.wryel.horas.util.FacesUtil;

public abstract class AbstractController<Bean extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	protected final Class<Bean> beanClass;

	protected Bean bean;
	
	protected List<Bean> list;

	protected String action;
	
	protected static final String ACTION = "action";
	
	protected static final String ACTION_ADD = "add";
	
	protected static final String ACTION_EDIT = "edit";
	
	public static final String ENTITY = "entity";
	
	@PostConstruct
	public void debugConstruct() {
		System.out.println("Criando instancia de " + getClass().getSimpleName());
		if (FacesUtil.getInstance().getFlashScope().containsKey(flahKey())) {
			setBean(beanClass.cast(FacesUtil.getInstance().getFlashScope().get(flahKey())));
		}
	}
	
	@PreDestroy
	public void debugPreDestroy() {
		System.out.println("Removendo instancia de " + getClass().getSimpleName());
	}
	
	public AbstractController(Class<Bean> beanClass) {
		this.beanClass = beanClass;
	}
	
	/**
	 * @return the list
	 */
	public List<Bean> getList() {
		if (list == null) {
			list = new ArrayList<>();
		}
		return list;
	}
	
	public List<SelectItem> getListAsSelectItems() {
		List<SelectItem> listaAsSelectItems = FacesUtil.getInstance().createSelectItem(getList(), "nome");
		return listaAsSelectItems;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<Bean> list) {
		this.list = list;
	}

	/**
	 * @param bean the bean to set
	 */
	public void setBean(Bean bean) {
		this.bean = bean;
	}
	
	/**
	 * @return the bean
	 */
	public Bean getBean() {
		return bean;
	}

	public void cleanList() {
		this.list = new ArrayList<>();
	}
	
	public void cleanBean() {
		try {
			this.bean = beanClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new HorasRuntimeException("Não foi possível limpar o bean da entidade " + beanClass.getClass(), e);
		}
	}
	
	public void clean() {
		cleanBean();
		cleanList();
	}
	
	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String nav(String nextViewId) {
		String lastViewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
		return lastViewId.contains(nextViewId) ? Navegacao.ATUAL : nextViewId;
	}

	public void retrieveBeanFromFlashEvent() {
		if (!FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest()) {
			bean = beanClass.cast(FacesUtil.getInstance().getFlashScope().get(flahKey()));			
		}
	}
	
	public String flahKey() {
		return "entity_" + getClass().getSimpleName(); 
	}
}
